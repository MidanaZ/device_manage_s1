package vn.edu.siu.ailab.devicemanager.service.implement;

import vn.edu.siu.ailab.devicemanager.entity.SlipCondition;
import vn.edu.siu.ailab.devicemanager.repository.ISlipConditionRepo;
import vn.edu.siu.ailab.devicemanager.service.ISlipConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SlipConditionServiceImpl implements ISlipConditionService {
    @Autowired
    ISlipConditionRepo repository;
    @Override
    public Iterable<SlipCondition> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SlipCondition> findById(int id) {
        return Optional.empty();
    }

    @Override
    public SlipCondition save(SlipCondition slipCondition) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
