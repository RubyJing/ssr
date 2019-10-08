import com.ssm.concurrent.TaskExecutorExample1;
import com.ssm.dao.YZJHViewPointMapper;
import com.ssm.entity.SchoolBusStudent;
import com.ssm.entity.YZJHViewPoint;
import com.ssm.service.SchoolBusStudentService;
import com.ssm.service.YZJHViewPointService;
import com.ssm.util.UUIDUtil;
import com.ssm.vo.YZJHViewPointVo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/spring/spring-mybatis.xml",
        "classpath*:/config/spring/spring-mvc.xml"})
public class YZJHViewPointTest {
    @Autowired
    private YZJHViewPointMapper yzjhViewPointMapper;

    @Autowired
    private YZJHViewPointService yzjhViewPointService;

    @Autowired
    private SchoolBusStudentService schoolBusStudentService;

    @Autowired
    private TaskExecutorExample1 taskExecutorExample;




    @Ignore
    @Test
    public void selectVo(){
        yzjhViewPointService.getAllRootViewPoint("1","1");
    }


   @Test
   @Ignore
    public void addYZJHViewPoint(){
        YZJHViewPointVo yzjhViewPoint = new YZJHViewPointVo();
        yzjhViewPoint.setPid(UUIDUtil.getId());
        yzjhViewPoint.setContent("测试线程");
        yzjhViewPoint.setStates('1');
        yzjhViewPoint.setGxrId("1");
        yzjhViewPoint.setZxDate(new Date());
        long startTime = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            yzjhViewPointService.addViewPoint(yzjhViewPoint,"1");
        }
       long endTime = System.currentTimeMillis();
        System.out.println("花费时间："+(endTime-startTime)+"ms");
    }

    @Ignore
    @Test
    public void deleteYZJHViewPoint(){
        int result = yzjhViewPointMapper.deleteYZJHViewPointByPid("17f8504d9e6341ca84bd03505efdfedf");
       //成功result = 1
        System.out.println(result);
    }
    @Ignore
    @Test
    public void updateYZJHViewPoint(){
        YZJHViewPoint yzjhViewPoint = new YZJHViewPoint();
        yzjhViewPoint.setPid("6bf4c7665cec4b0892484244a0a1f281");
        yzjhViewPoint.setContent("测试更新");
        yzjhViewPoint.setGxr("2");
        yzjhViewPoint.setGxsj(new Date());
        int result = yzjhViewPointMapper.updateYZJHViewPoint(yzjhViewPoint);
        //成功result = 1
        System.out.println(result);
    }
    @Ignore
    @Test
    public void  selectYZJHViewPoint(){
        YZJHViewPoint yzjhViewPoint = new YZJHViewPoint();
        yzjhViewPoint.setContent("测试内容");
        List<YZJHViewPoint> viewPoints = yzjhViewPointMapper.selectYZJHViewPoint(yzjhViewPoint);
        System.out.println(viewPoints.size());
    }

    @Ignore
    @Test
    public void test(){
        yzjhViewPointService.getAllRootViewPoint2("1","1");
    }
    @Test
    public void insert(){
        long startTime = System.currentTimeMillis();
//        taskExecutorExample.insertBusLink();
        taskExecutorExample.insertBusStation();
//        taskExecutorExample.insertBusBus();
//       taskExecutorExample.insertBusStudent();
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间："+(endTime-startTime)+"ms");
    }

    @Ignore
    @Test
    public void find(){
        long startTime = System.currentTimeMillis();
        List<SchoolBusStudent> schoolBusStudents = schoolBusStudentService.find();
        System.out.println(schoolBusStudents.size());
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间："+(endTime-startTime)+"ms");
        //1244ms
    }
    @Ignore
    @Test
    public void findByThreadPool(){
        int threadSize = 1;
        long startTime = System.currentTimeMillis();
        //线程同步计数器
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        taskExecutorExample.find(countDownLatch,threadSize);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间："+(endTime-startTime)+"ms");
        //2ms
    }

}
