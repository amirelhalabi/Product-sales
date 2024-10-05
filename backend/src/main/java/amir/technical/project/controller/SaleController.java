package amir.technical.project.controller;

import amir.technical.project.model.Product;
import amir.technical.project.model.Sale;
import amir.technical.project.model.SaleTransaction;
import amir.technical.project.service.ProductService;
import amir.technical.project.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Sale sale = saleService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return ResponseEntity.ok(createdSale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale saleDetails) {
        Sale updatedSale = saleService.updateSale(id, saleDetails);
        return ResponseEntity.ok(updatedSale);
    }

     @PutMapping("/{id}/transactions/add")
    public ResponseEntity<Sale> updateSaleTransactions(@PathVariable Long id, @RequestBody List<SaleTransaction> saleDetails) {
        Sale updatedSale = saleService.addSaleTransactions(id, saleDetails);
        return ResponseEntity.ok(updatedSale);
    }

    @PutMapping("/{id}/transactions/edit")
    public ResponseEntity<Sale> editSaleTransactions(@PathVariable Long id, @RequestBody List<SaleTransaction> saleDetails) {
        Sale updatedSale = saleService.updateSaleTransactions(id, saleDetails);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleById(@PathVariable Long id) {
        saleService.deleteSaleById(id);
        return ResponseEntity.noContent().build();
    }

}
