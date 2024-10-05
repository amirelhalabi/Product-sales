package amir.technical.project.service;

import amir.technical.project.model.Client;
import amir.technical.project.model.Product;
import amir.technical.project.model.Sale;
import amir.technical.project.model.SaleTransaction;
import amir.technical.project.repository.ClientRepository;
import amir.technical.project.repository.ProductRepository;
import amir.technical.project.repository.SaleRepository;
import amir.technical.project.repository.SaleTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleTransactionRepository saleTransactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(SaleService.class);

    public Sale createSale2(Sale sale) {
        Sale savedSale = saleRepository.save(sale);
        for (SaleTransaction transaction : sale.getTransactions()) {
            transaction.setSale(savedSale);
            saleTransactionRepository.save(transaction);
        }
        return savedSale;
    }
    public Sale createSale(Sale sale) {
        Client client = clientRepository.findById(sale.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Client seller = clientRepository.findById(sale.getSeller().getId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        sale.setClient(client);
        sale.setSeller(seller);

        return saleRepository.save(sale);
    }

    public Sale createSale12(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    public Sale updateSale(Long id, Sale saleDetails) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
        sale.setTotal(saleDetails.getTotal());
        return saleRepository.save(sale);
    }

    public void deleteSaleById(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Sale not found with id: " + id);
        }
        saleRepository.deleteById(id);
    }

    //edit saleTransaction
    public Sale updateSaleTransactions(Long saleId, List<SaleTransaction> saleTransactions) {
        logger.info("Attempting to update Sale Transactions for Sale ID: {}", saleId);
        Sale sale = getSaleById(saleId); // Fetch the sale by ID

        for (SaleTransaction incomingTransaction : saleTransactions) {
            if (incomingTransaction.getId() != null) { // Check if transaction ID is provided
                Optional<SaleTransaction> existingTransactionOpt = sale.getTransactions().stream()
                        .filter(t -> t.getId().equals(incomingTransaction.getId()))
                        .findFirst();

                if (existingTransactionOpt.isPresent()) {
                    SaleTransaction existingTransaction = existingTransactionOpt.get();

                    if (incomingTransaction.getProduct() != null && incomingTransaction.getProduct().getId() != null) {
                        Product fullProduct = productRepository.findById(incomingTransaction.getProduct().getId())
                                .orElseThrow(() -> new RuntimeException("Product not found"));
                        existingTransaction.setProduct(fullProduct); // Update product info
                    }

                    existingTransaction.setQuantity(incomingTransaction.getQuantity());
                    existingTransaction.setPrice(incomingTransaction.getPrice());
                    logger.info("Updated Transaction ID: {}, Quantity: {}, Price: {}",
                            existingTransaction.getId(), existingTransaction.getQuantity(), existingTransaction.getPrice());
                } else {
                    logger.warn("Transaction ID not found for update: {}", incomingTransaction.getId());
                }
            } else {
                logger.warn("Transaction ID is missing in the request for an update.");
            }
        }

        return saleRepository.save(sale); // Save the updated sale
    }
    //add new transaction
    public Sale addSaleTransactions(Long saleId, List<SaleTransaction> saleTransactions) {
        logger.info("Attempting to add new Sale Transactions for Sale ID: {}", saleId);
        Sale sale = getSaleById(saleId); // Fetch the sale by ID

        for (SaleTransaction newTransaction : saleTransactions) {
            if (newTransaction.getId() == null) { // Ensure that the transaction is new
                if (newTransaction.getProduct() != null && newTransaction.getProduct().getId() != null) {
                    Product fullProduct = productRepository.findById(newTransaction.getProduct().getId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    newTransaction.setProduct(fullProduct); // Set product
                }

                newTransaction.setSale(sale); // Associate the sale with the transaction
                sale.getTransactions().add(newTransaction); // Add the transaction to the sale
                logger.info("Added new Transaction with Quantity: {}, Price: {}",
                        newTransaction.getQuantity(), newTransaction.getPrice());
            } else {
                logger.warn("New Transaction should not have an ID: {}", newTransaction.getId());
            }
        }

        return saleRepository.save(sale); // Save the updated sale
    }

}
