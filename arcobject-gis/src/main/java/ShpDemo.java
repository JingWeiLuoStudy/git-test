import com.esri.arcgis.carto.MapServer;
import com.esri.arcgis.datasourcesGDB.SdeWorkspaceFactory;
import com.esri.arcgis.datasourcesfile.ShapefileWorkspaceFactory;
import com.esri.arcgis.geodatabase.*;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.system.*;


public class ShpDemo {


    public static void main(String[] args) throws Exception{
        String path = "F:\\dedk_source\\DK2";
        System.out.println("...........parse shapefile...............");
        EngineInitializer.initializeEngine();
        AoInitialize aoInit = new AoInitialize();
        aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
        initializeArcGISLicenses(aoInit);

        new MapServer();

        ShapefileWorkspaceFactory factory = new ShapefileWorkspaceFactory();

        IFeatureWorkspace workspace = (IFeatureWorkspace)factory.openFromFile(path,0);
        IFeatureClass iFeatureClass = workspace.openFeatureClass("dk2sub");
        IFeatureCursor iFeatureCursor=iFeatureClass.search(null,false);
        IFeature iFeature=iFeatureCursor.nextFeature();
        while(iFeature!=null){
            System.out.println(iFeature.getShape());
            iFeature=iFeatureCursor.nextFeature();
        }
    }
    private static void initializeArcGISLicenses(AoInitialize aoInit) {
        try {
            if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeBasic)
                    == esriLicenseStatus.esriLicenseAvailable)
                aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeBasic);
            else if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeStandard)
                    == esriLicenseStatus.esriLicenseAvailable)
                aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeStandard);
            else if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeAdvanced)
                    == esriLicenseStatus.esriLicenseAvailable)
                aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeAdvanced);
        } catch (Exception e) {e.printStackTrace();}
    }

}
