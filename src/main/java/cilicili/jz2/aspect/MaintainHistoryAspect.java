package cilicili.jz2.aspect;

import cilicili.jz2.base.BaseDomain;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Aspect
@Repository("maintainHistoryAspect")
public class MaintainHistoryAspect {
  final static Logger logger = LoggerFactory.getLogger(MaintainHistoryAspect.class);

  @Before("execution(public * cilicili.jz2..*.*Mapper.add*(..)) &&  args(baseDomain,..)")
  public void appendCreateInfo(BaseDomain baseDomain) {
    baseDomain.setCreateDate(new Date());
  }

  @Before("execution(public * cilicili.jz2..*.*Mapper.update*(..)) &&  args(baseDomain,..)")
  public void appendUPdateInfo(BaseDomain baseDomain) {
    baseDomain.setUpdateDate(new Date());
  }

}
