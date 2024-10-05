package amir.technical.project.repository;

import amir.technical.project.model.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Long> {}
