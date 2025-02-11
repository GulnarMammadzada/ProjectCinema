class Actor {
    String name;
    int age;
    String surname;

    public Actor(String name,String surname, int age) {
        this.name = name;
        this.age = age;
        this.surname = surname;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    @Override
    public String toString() {
        return name +"  " +surname+ " (Age: " + age + ")";
    }
}

