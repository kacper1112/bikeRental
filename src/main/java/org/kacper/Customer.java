package org.kacper;

public final class Customer {
    private final int id;
    private final String name;
    private final String surname;
    private final String pesel;
    
    //optional 
    private final String phone;
    private final String email;
    private final int permanentDiscount;
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getPermanentDiscount() {
        return permanentDiscount;
    }
    
    public String toString() {
        String phone = this.getPhone();
        String email = this.getEmail();
        int discount = this.getPermanentDiscount();


        return
            this.getName() + "\n" +
            this.getSurname() + "\n" +
            this.getPesel() + "\n" +
            ((phone == null) ? "" : phone + "\n") +
            ((email == null) ? "" : email + "\n") +
            ((discount == 0) ? "" : "stała zniżka:" + discount) + "\n\n";
    }

    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.pesel = builder.pesel;
        this.phone = builder.phone;
        this.email = builder.email;
        this.permanentDiscount = builder.discount;
    }

    public static class CustomerBuilder {
        private final int id;
        private final String name;
        private final String surname;
        private final String pesel;

        //optional 
        private String phone;
        private String email;
        private int discount;

        public CustomerBuilder(int id, String name, String surname, String pesel) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.pesel = pesel;
        }

        public CustomerBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder withDiscount(int discount) {
            this.discount = discount;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}


