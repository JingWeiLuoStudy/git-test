package gisq.opengis.arconjects;

import com.esri.arcgis.carto.MapServer;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;

import java.io.IOException;

/**
 * Created by ljw 2020/8/10
 */
public class EngineHelloWorld {


//    private static String DevKitInstallDir = "C:/Program Files (x86)/ArcGIS/DeveloperKit10.4";

    public static void main(String[] args) {
        try {
            // 1.初始化java组件对象模型(COM)互操作
            EngineInitializer.initializeEngine();
            //2.初始化ArcGIS许可
            AoInitialize aoInit = new AoInitialize();
            initializeArcGISLicenses(aoInit);
            //3.调用ArcObjects
            MapServer mapServer = new MapServer();
            String DevKitInstallDir = System.getenv("AGSDEVKITJAVA");
            System.out.println("========="+DevKitInstallDir+"=========");
            mapServer.connect(DevKitInstallDir+"/java/samples/data/mxds/brazil.mxd");
            String defaultMapName = mapServer.getDefaultMapName();
            System.out.println("Hello ArcObjects!:"+defaultMapName);
            //4.释放许可
            aoInit.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeArcGISLicenses(AoInitialize aoInit) {
        try{
            if (aoInit.isProductCodeAvailable
                    (esriLicenseProductCode.esriLicenseProductCodeEngine) ==
                    esriLicenseStatus.esriLicenseAvailable){
                aoInit.initialize
                        (esriLicenseProductCode.esriLicenseProductCodeEngine);
            }
            else if (aoInit.isProductCodeAvailable
                    (esriLicenseProductCode.esriLicenseProductCodeBasic) ==
                    esriLicenseStatus.esriLicenseAvailable){
                aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeBasic)
                ;
            }
            else{
                System.err.println(
                        "Engine Runtime or Desktop Basic license not initialized.");
                System.err.println("Exiting application.");
                System.exit( - 1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
