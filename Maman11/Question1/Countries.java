
package CountryGuess;


/**
 * Declaring an enum type with a constructors and explicit instance fields and
 * accessors for these fields. List of countries.
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 14/03/19
 */
import java.util.EnumSet;

public enum Countries {
    //declare constans of enum type
    country1("venezuela"),
    country2("thailand"),
    country3("switzerland"),
    country4("israel"),
    country5("netherlands"),
    country6("finland"),
    country7("montenegro"),
    country8("liechtenstein"),
    country9("jamaica"),
    country10("argentina"),
    country11("indonesia"),
    country12("hungary"),
    country13("germany"),
    country14("france"),
    country15("slovenia"),
    country16("ethiopia"),
    country17("colombia"),
    country18("cameroon"),
    country19("ecuador"),
    country20("ukraine"),
    country21("portugal"),
    country22("belgium"),
    country23("australia"),
    country24("vietnam"),
    country25("uruguay"),
    country26("singapore"),
    country27("bangladesh"),
    country28("georgia"),
    country29("honduras"),
    country30("ireland"),
    country31("tanzania"),
    country32("luxembourg"),
    country33("macedonia"),
    country34("nicaragua"),
    country35("romania");
    //instance fields
    private final String _country;

    // enum constructor
    Countries(String country) {
        this._country = country;
    }

    // accessor for field title
    public String getCountry() {
        return this._country;
    }

}//end enum Countries
