package spring9_orm.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.TypeAlias;

/**
 * 懒加载禁用：不推荐生产使用。
 * 由于session是由Hibernate管理的，完成dao操作后会自动关闭导致。（懒加载导致异常）
 * @author TD
 *
 */
@Entity
@Table(name="td_student")
@Proxy(lazy=false)
public class User {
	
	private int id;
	private String name;
	private long sex;
	private long age;
	private String phone;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSex() {
		return sex;
	}
	public void setSex(long sex) {
		this.sex = sex;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
