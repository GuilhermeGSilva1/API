package api_teste.ds.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {

    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    public Task() {
    }

    public Task(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Task task = (Task) obj;

        return java.util.Objects.equals(id, task.id)
                && java.util.Objects.equals(user, task.user)
                && java.util.Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, user, description);
    }
}