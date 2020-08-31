import com.esri.arcgis.carto.MapServer;
import com.esri.arcgis.datasourcesGDB.SdeWorkspaceFactory;
import com.esri.arcgis.geodatabase.*;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.system.*;


public class SdeDemo {


    public static void main(String[] args) throws Exception{
        System.out.println("...........begin connect sde...............");
        EngineInitializer.initializeEngine();
        AoInitialize aoInit = new AoInitialize();
        aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
        initializeArcGISLicenses(aoInit);

        new MapServer();

        initializeArcGISLicenses(aoInit);
        SdeWorkspaceFactory factory = new SdeWorkspaceFactory();
        PropertySet propertySet = new PropertySet();
        propertySet.setProperty("Server", "192.168.11.85");
        propertySet.setProperty("Instance", "5151");
        propertySet.setProperty("User", "sde");
        propertySet.setProperty("Password", "sde");
        IFeatureWorkspace workspace = (IFeatureWorkspace) factory.open(propertySet, 0);
        System.out.println(workspace);
        String tableName = "SDE.ST_ZD";
        IFeatureClass iFeatureClass = workspace.openFeatureClass(tableName);
        IQueryFilter iQueryFilter=new QueryFilter();
        iQueryFilter.setWhereClause("");
        ISpatialFilter iSpatialFilter= new SpatialFilter();
        iSpatialFilter.setWhereClause("");
        IGeometry geometry=null;
        iSpatialFilter.setGeometryByRef(geometry);
        iSpatialFilter.setSpatialRel(1);
        IFeatureCursor iFeatureCursor=iFeatureClass.search(iSpatialFilter,false);
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
