package vn.edu.siu.ailab.devicemanager.repository;

import vn.edu.siu.ailab.devicemanager.entity.SlipCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISlipConditionRepo extends JpaRepository<SlipCondition,Integer> {
}
