package org.dizzyfox734.todo_list.domain.todo;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean completed_fl;

    public Todos() {
        super();
    }

    public static class Builder {
        private Long id;
        private String content;
        private boolean completed_fl;

        public Builder(String content, boolean completed_fl) {
            this.content = content;
            this.completed_fl = completed_fl;
        }

        public Todos build() {
            return new Todos(this);
        }
    }
    private Todos(Builder builder) {
        this.content = builder.content;
        this.completed_fl = builder.completed_fl;
    }

    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public boolean getCompleted() {
        return completed_fl;
    }

    public void update(String content, boolean completed_fl) {
        this.content = content;
        this.completed_fl = completed_fl;
    }
}
