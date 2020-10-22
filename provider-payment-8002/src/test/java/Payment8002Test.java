
import com.Payment8002;
import com.dao.PaymentDao;
import com.pojo.Payment;
import com.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Payment8002.class)
@Slf4j
public class Payment8002Test {

    @Autowired
    PaymentDao paymentDao;
    @Autowired
    PaymentService paymentService;

    @org.junit.jupiter.api.Test
    public void test1(){
        Payment payment = paymentService.getPaymentById(1l);
        log.debug(payment+"dsf");
        System.out.println(payment);
    }
}
