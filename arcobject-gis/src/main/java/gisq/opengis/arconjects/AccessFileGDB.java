package gisq.opengis.arconjects;

import java.io.File;

import com.esri.arcgis.datasourcesGDB.FileGDBWorkspaceFactory;
import com.esri.arcgis.geodatabase.FeatureClass;
import com.esri.arcgis.geodatabase.IDataset;
import com.esri.arcgis.geodatabase.IDatasetName;
import com.esri.arcgis.geodatabase.IEnumDataset;
import com.esri.arcgis.geodatabase.IEnumDatasetName;
import com.esri.arcgis.geodatabase.Workspace;
import com.esri.arcgis.geodatabase.esriDatasetType;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;

/**
 * Created by ljw 2020/8/11
 */
public class AccessFileGDB {

    public AccessFileGDB(){

    }

    public static void main(String[] args) {
        System.out.println("Starting BrowseFileGDB - An ArcObjects SDK Developer Sample");
        try{
            //Initialize engine console application
            EngineInitializer.initializeEngine();

            //Initialize ArcGIS license
            AoInitialize aoInit = new AoInitialize();
            initializeArcGISLicenses(aoInit);

            //Get DEVKITHOME Home
            String devKitHome = System.getenv("AGSDEVKITJAVA");

            //Data access setup
            String inFGDB = devKitHome + "java" + File.separator + "samples" + File.separator
                    + "data" + File.separator + "usa"     + File.separator
                    + "usa.gdb";
            System.out.println("Input is " + inFGDB);

            AccessFileGDB accessFileGDB = new AccessFileGDB();
            accessFileGDB.browseFileGDB(inFGDB);

            System.out.println("Done.");

            //Ensure any ESRI libraries are unloaded in the correct order
            aoInit.shutdown();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            System.out.println("Sample failed.  Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void initializeArcGISLicenses(AoInitialize aoInit) {
        try {
            if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine)
                    == esriLicenseStatus.esriLicenseAvailable)
                aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
            else if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeBasic)
                    == esriLicenseStatus.esriLicenseAvailable)
                aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeBasic);
            else{
                System.err.println("Could not initialize an Engine or Basic License. Exiting application.");
                System.exit(-1);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    private void browseFileGDB(String inFGDB){
        try{
            FileGDBWorkspaceFactory factory = new FileGDBWorkspaceFactory();
            Workspace workspace = new Workspace(factory.openFromFile(inFGDB, 0));

            //Get all dataset names in the workspace
            IEnumDatasetName enumDatasetName = workspace.getDatasetNames(esriDatasetType.esriDTAny);

            //Get the first name in the dataset
            IDatasetName dsName = enumDatasetName.next();
            while(dsName != null){
                //Print out the dataset name to the console
                System.out.println("Dataset Name: " + dsName.getName());

                //Get the next name in the enumeration of dataset names
                dsName = enumDatasetName.next();
            }

            //Get all the datasets
            IEnumDataset enumDataset = workspace.getDatasets(esriDatasetType.esriDTFeatureClass);

            //Get the first dataset
            IDataset ds = enumDataset.next();
            while(ds != null){
                FeatureClass fClass = new FeatureClass(ds);

                int fCount = fClass.featureCount(null);

                System.out.println("FeatureClass " + fClass.getAliasName() + " has "
                        + fCount + " features.");


                ds = enumDataset.next();
            }
        }catch(Exception e){e.printStackTrace();}
    }

}
