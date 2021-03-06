/*
 * TCSS 305 – Winter 2018 Assignment 2
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Creates an item class.
 * 
 * @author Leul Amare
 * @version 01/24/2018
 *
 */

public final class Item {

    // Default constructor.

    /**
     * A default QUANTITY to use when no other QUANTITY is specified.
     */
    private static final int DEFAULT_BULK_QUANTITY = 0;

    /**
     * A default BULKPRICE to use when no other BULKPRICE is specified.
     */
    private static final BigDecimal DEFAULT_BULK_PRICE = BigDecimal.valueOf(0.0);

    // instance field.

    /**
     * The name of the item.
     */
    private final String myName;

    /**
     * The price of the item.
     */
    private final BigDecimal myRegularPrice;

    /**
     * The bulk quantity of the item.
     */
    private final int myBulkQuantity;

    /**
     * The bulk price of the item.
     */
    private final BigDecimal myBulkPrice;

    /**
     * Constructs a method that contains theName and thePrice of the item with the.
     * 
     * @param theName the name of the item.
     * @param thePrice the price of the item.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        this(theName, thePrice, DEFAULT_BULK_QUANTITY, DEFAULT_BULK_PRICE);
    }

    /**
     * Constructs an item of that has a given name, price, 
     * bilk quantity and bulk price.
     * 
     * @param theName          The name to assign to this item.
     * @param thePrice         The price to assign to this item.
     * @param theBulkQuantity  The bulk quantity to assign to this item.
     * @param theBulkPrice     The bulk price to assign to this item.
     * @throws IllegalArgumentException If the bulk quantity is 0 or less.
     * @throws IllegalArgumentException If the bulk price is 0 or less.
     */
    public Item(final String theName, final BigDecimal thePrice, 
                final int theBulkQuantity, final BigDecimal theBulkPrice) {
              
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException(" The bulk quantity can't be zero!");
            
        }
        
        if (theBulkPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The bulk price can't be zero!");
            
        }
        
        // checks if an empty string has been passed.
        if (theName.isEmpty()) {
            throw new IllegalArgumentException("The Name of the item can't be empty!");

        }
        
        // checks for 0 or negative value.
        if (thePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The Price can't be less than zero!");
            
        }
        
        
        myName = Objects.requireNonNull(theName, "The Name can't be a null!");
        
        myRegularPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
            
    }

    /**
     * @return the name for this item.
     */
    public String getName() {

        return myName;
    }

    /**
     * @return the price for a single item.
     */
    public BigDecimal getPrice() {

        return myRegularPrice;
    }

    /**
     * @return the bulk quantity for this item.
     */
    public int getBulkQuantity() {

        return myBulkQuantity;
    }

    /**
     * @return the bulk price for this item
     */
    public BigDecimal getBulkPrice() {

        return myBulkPrice;
    }

    /**
     * @return true if the item has a bulk pricing, false otherwise.
     */
    public boolean isBulk() {

        return myBulkQuantity > 0;
    }
    
    // Overridden methods form class object.
    
    /**
     * {@inheritDoc}
     * Returns a String representation of the this item.
     */
    @Override
    public String toString() {
        /**
         * Currency format in US dollars.
         * Constructs a String representation of the price.
         */
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        
        final StringBuilder sb = new StringBuilder();
        sb.append(myName);
        sb.append(" , ");
        sb.append(nf.format(myRegularPrice));
        
        // checks for bulk items.
        if ((myBulkQuantity != 0) && (myBulkPrice != null)) {
            sb.append(" (" + myBulkQuantity + "for" + nf.format(getBulkPrice()) + ")");

        }
        return sb.toString();
    }
    
    /**
     * Returns true if the specified object is equivalent to this Item, and false otherwise.
     * Two items are equivalent if they have the exactly equivalent name, prices, 
     * bulk quantity and bulk price. 
     * 
     * {@inheritDoc}  
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean result = false;
        
        if ((theOther != null) && theOther.getClass() == this.getClass()) {
            final Item otherItem = (Item) theOther;
            
            // Checks for the bulk item.
            if ((myBulkQuantity != 0) && (myBulkPrice != null)) {
                
                result = myName.equals(otherItem.myName)
                        && (myRegularPrice.equals(otherItem.myRegularPrice))
                        && myBulkQuantity == otherItem.myBulkQuantity 
                        && myBulkPrice.equals(otherItem.myBulkPrice);
               
             // Single item check.  
            } else {
                result = myName.equals(otherItem.myName) 
                                && myRegularPrice.equals(otherItem.myRegularPrice);
            }
                
        }
        

        return result;
    }
   
    /**
     * Returns an integer has code for this item.
     * 
     * {@inheritDoc} 
     */

    @Override
    public int hashCode() {
        int hashResult;
        
        // single item hashCode check.
        hashResult = Objects.hash(myName, myRegularPrice);
        
        // bulk item hashCode check.
        if ((myBulkQuantity != 0) && (myBulkPrice != null)) {
            hashResult = Objects.hash(myName, myRegularPrice, 
                                      myBulkQuantity, myBulkPrice);
            
        }
        

        return hashResult;
    }

}

