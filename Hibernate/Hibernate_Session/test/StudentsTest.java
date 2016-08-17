import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//������
public class StudentsTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		//�������ö���
		Configuration config = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//�����Ự����
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//�Ự����
		session = sessionFactory.openSession();
		//��������
		//transaction = session.beginTransaction();		
	}
	@Test
	public void testSaveStudents() {
		
		//����ѧ������
		Students s=new Students(1, "����", "Ů", new Date(), "����");
		session.doWork(new Work(){ //��������

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				connection.setAutoCommit(true);
			}
			
		});
		//Students s=new Students(2, "����", "��", new Date(), "���");
	    //����ʹ��sql���������棬����ʹ��hibernate�����浽���ݿ�
	    session.save(s);
	    session.flush();
	}
	@After
	public void destroy() {
		//transaction.commit();//�ύ����
		session.close();//�رջỰ
		sessionFactory.close();//�رջỰ����
	}

}