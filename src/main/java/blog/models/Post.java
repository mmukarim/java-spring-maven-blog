package blog.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 300)
	private String title;
	
	@Lob
	@Column(nullable = false)
	private String body;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User author;
	
	@Column(nullable = false)
	private Date date = new Date();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Post() {
	}
	public Post(String title, String body, User author) {
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = new Date();
	}
	public Post(Long id, String title, String body, User author) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = new Date();
	}
	public Post(String title, String body, User author, Date date) {
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = date;
	}
	public Post(Long id, String title, String body, User author, Date date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Post {" +
				"id=" + id + 
				", title=" + title + 
				", body=" + body + 
				", author=" + author +
				", date=" + date +
				"}";
	}
	
}
