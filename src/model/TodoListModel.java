package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * TODOリストモデルクラス
 */
public class TodoListModel {

	private int id;
	private Date expirationDate;
	private String todoItem;
	private int isCompleted;
	private int isDeleted;
	private Timestamp createDateTime;
	private Timestamp updateDateTime;

	public TodoListModel() {
//		処理なし
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getTodoItem() {
		return this.todoItem;
	}

	public void setTodoItem(String todoItem) {
		this.todoItem = todoItem;
	}

	public int getIsCompleted() {
		return this.isCompleted;
	}

	public void setIsCompleted(int isCompleted) {
		this.isCompleted = isCompleted;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Timestamp getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
}
