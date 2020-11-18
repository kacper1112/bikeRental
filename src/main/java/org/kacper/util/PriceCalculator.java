package org.kacper.util;

import org.kacper.Rental;

public interface PriceCalculator {
    double calculatePrice(Rental rental);
    
}
