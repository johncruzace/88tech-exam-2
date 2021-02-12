package johncruz.tech.exam.minibank.service.impl;

import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import johncruz.tech.exam.minibank.service.DepositService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DepositServiceImpl implements DepositService {

    public ResponseObject transactDepositTransaction(BankTransaction bankTransaction){



        return new ResponseObject();
    }

}
