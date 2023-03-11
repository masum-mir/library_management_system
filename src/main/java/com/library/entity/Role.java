/*
 * package com.library.entity;
 * 
 * import java.util.ArrayList; import java.util.List; import
 * com.fasterxml.jackson.annotation.JsonIgnore;
 * 
 * import jakarta.persistence.Column; import jakarta.persistence.Entity; import
 * jakarta.persistence.EnumType; import jakarta.persistence.Enumerated; import
 * jakarta.persistence.GeneratedValue; import
 * jakarta.persistence.GenerationType; import jakarta.persistence.Id; import
 * jakarta.persistence.OneToMany; import jakarta.persistence.Table;
 * 
 * @Entity
 * 
 * @Table(name = "roles") public class Role {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO) private long id;
 * 
 * @Enumerated(EnumType.STRING)
 * 
 * @Column(length = 20) public ERole name;
 * 
 * 
 * 
 * public Role() { super(); }
 * 
 * public Role(long id, ERole name) { super(); this.id = id; this.name = name; }
 * 
 * public long getId() { return id; }
 * 
 * public void setId(long id) { this.id = id; }
 * 
 * public ERole getName() { return name; }
 * 
 * public void setName(ERole name) { this.name = name; }
 * 
 * 
 * 
 * // @OneToMany(mappedBy = "roles") // @JsonIgnore // private List<User> users
 * = new ArrayList<>(); // // public int getId() { // return id; // } // public
 * void setId(int id) { // this.id = id; // } // public List<User> getUsers() {
 * // return users; // } // public void setUsers(List<User> users) { //
 * this.users = users; // } // // @Override // public String toString() { //
 * return "Role [id=" + id + ", users=" + users + "]"; // }
 * 
 * }
 */