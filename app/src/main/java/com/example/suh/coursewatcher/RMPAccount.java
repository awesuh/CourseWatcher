package com.example.suh.coursewatcher;

/**
 * <h1>RMPAccount</h1>
 * The information needed to link to the RMP account, and number of ratings for that account
 *
 * A single professor may have more than one RMPAccount, hence the need for it to be in it's own class
 */
public class RMPAccount {
    public static final String TAG = "RMPAccount";

    public int id;
    public int numRatings;
}
