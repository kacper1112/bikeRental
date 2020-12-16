package org.kacper.repo;

import org.kacper.Customer;
import org.kacper.Employee;
import org.kacper.Rental;
import org.kacper.calc.PriceCalculatorConfig;
import org.kacper.rental_items.Accessory;
import org.kacper.rental_items.Bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RepoRowMapper {
    static Bike mapRowToBike(ResultSet rs) throws SQLException {

        return new Bike.Builder(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price_per_hour"),
                rs.getString("make"),
                rs.getString("frame_size"),
                rs.getString("wheel_size"))
                .withSuspension(rs.getString("suspension"))
                .withFrameNumber(rs.getString("frame_number"))
                .build();
    }

    static Accessory mapRowToAccessory(ResultSet rs) throws SQLException {

        return new Accessory(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price_per_hour"),
                rs.getString("description"),
                rs.getString("type")
        );
    }

    static Customer mapRowToCustomer(ResultSet rs) throws SQLException {

        return new Customer.CustomerBuilder(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("pesel"))
                .withPhone(rs.getString("phone"))
                .withEmail(rs.getString("email"))
                .withDiscount(rs.getInt("permanent_discount"))
                .build();
    }

    static Employee mapRowToEmployee(ResultSet rs) throws SQLException {

        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("pesel")
        );
    }

    static Rental mapRowToRental(ResultSet rs) throws SQLException {

        Rental rental = new Rental(
                rs.getInt("id"),
                rs.getObject("timeFrom", LocalDateTime.class),
                rs.getObject("timeTo", LocalDateTime.class),
                RepoGetOperation.getInstance()
                        .getCustomerById(rs.getInt("customer_id")), 
                RepoGetOperation.getInstance()
                        .getAllRentalItemsFromRentalId(rs.getInt("id"))
        );

        rental.setPriceCalculator(PriceCalculatorConfig.getCalculator(rental));

        return rental;
    }
}
