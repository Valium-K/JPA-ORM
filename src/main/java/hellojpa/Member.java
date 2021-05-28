package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// @Table(name = "MEMBER")      // table의 이름과 Entity class의 이름이 같으면 생략가능.
public class Member {
    @Id
    private Long id;

    // @Column(name = "name")   // table의 column과 필드명이 같으면 생략가능.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
