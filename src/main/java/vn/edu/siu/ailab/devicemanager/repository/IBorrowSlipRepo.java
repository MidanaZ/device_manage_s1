package vn.edu.siu.ailab.devicemanager.repository;

import vn.edu.siu.ailab.devicemanager.entity.BorrowSlip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBorrowSlipRepo extends JpaRepository<BorrowSlip,Integer> {
    List<BorrowSlip> findByBorrowerContains(String borrower);
}
