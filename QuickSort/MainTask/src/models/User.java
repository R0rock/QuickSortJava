package models;

import comparators.UserComparator;

import java.util.Comparator;

public class User implements Filterable<User>{
    private final Integer id;
    private final String name;
    private final String password;
    private final String email;
    // Чтобы не передавать в методы явным образом компараторы, мы один раз прописываем по какому полю будет любое сравнение элементов данного класса и если захотим в дальнейшем поменять его, то можно поменять лишь данный параметр
    private static Comparator<User> comp;

    static {
        comp = UserComparator.FullComparison.getFullComparison();
    }

    private User(Integer id, String name, String password, String email) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Integer getId() { return id; }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", \tname='" + name + '\'' +
                ", \tpassword='" + password + '\'' +
                ", \temail='" + email + '\'' +
                '}';
    }

    public static Comparator<User> getComp() {
        return comp;
    }

    public static void setComp(Comparator<User> comp) {
        User.comp = comp;
    }

    @Override
    public int compTo(User obj) {
        return comp.compare(this, obj);
    }

    @Override
    public boolean isEven() {
        return id % 2 == 0;
    }

    public static class UserBuilder {
        private Integer id;
        private String name;
        private String password;
        private String email;

        public UserBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(id, name, password, email);
        }
    }
}