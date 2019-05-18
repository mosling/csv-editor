package net.msk.csvedit;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CSVProperties
{
    static Logger LOGGER = LogManager.getLogger( CSVProperties.class );

    // @formatter:off
    @Getter @Setter private Character separationSign = ',';
    @Getter @Setter private Character quotationSign  = '"';
    @Getter @Setter private Integer dividerLocation  = -1;
    @Getter @Setter private Boolean treeVisible      = false;
    @Getter @Setter private Boolean largeIcons       = false;
    @Getter @Setter private String currentFolder     = System.getProperty( "user.home" );
    // @formatter:on

    public CSVProperties()
    {
    }

}