package gisq.opengis.arconjects;

import com.esri.arcgis.beans.TOC.TOCBean;
import com.esri.arcgis.beans.map.MapBean;
import com.esri.arcgis.beans.toolbar.ToolbarBean;
import com.esri.arcgis.controls.ControlsMapFullExtentCommand;
import com.esri.arcgis.controls.ControlsMapPanTool;
import com.esri.arcgis.controls.ControlsMapZoomInTool;
import com.esri.arcgis.controls.ControlsMapZoomOutTool;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.systemUI.esriCommandStyles;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by ljw 2020/8/10
 */
public class EngineVisualBeans {

    public static void main(String[] args) {
        try {
            // 1.初始化java组件对象模型(COM)互操作
            EngineInitializer.initializeVisualBeans();
            //2.初始化ArcGIS许可
            AoInitialize aoInit = new AoInitialize();
            aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
            MapBean map = new MapBean();
            //创建一个地图可视化组件并加载一个.mxd地图文档
            String DevKitInstallDir = System.getenv("AGSDEVKITJAVA");
            System.out.println(DevKitInstallDir);
            map.loadMxFile(DevKitInstallDir + "/java/samples/data/mxds/World.mxd", null, null);
            //创建工具栏并添加工具
            ToolbarBean toolbar = new ToolbarBean();
            toolbar.addItem(ControlsMapZoomInTool.getClsid(), 0, 0, false, 0,
                    esriCommandStyles.esriCommandStyleIconOnly);
            toolbar.addItem(ControlsMapZoomOutTool.getClsid(), 0, 0, false, 0,
                    esriCommandStyles.esriCommandStyleIconOnly);
            toolbar.addItem(new ControlsMapFullExtentCommand(), 0,  - 1, false, 0,
                    esriCommandStyles.esriCommandStyleIconOnly);
            toolbar.addItem(ControlsMapPanTool.getClsid(), 0, 0, false, 0,
                    esriCommandStyles.esriCommandStyleIconOnly);
            //
            TOCBean toc = new TOCBean();
            toolbar.setBuddyControl(map);
            toc.setBuddyControl(map);
            JFrame frame = new JFrame("Hello Arcobjects");
            frame.add(map, BorderLayout.CENTER);
            frame.add(toolbar, BorderLayout.NORTH);
            frame.add(toc, BorderLayout.WEST);
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
