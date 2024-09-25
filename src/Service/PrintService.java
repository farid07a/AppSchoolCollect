/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.InputStream;
import java.sql.Connection;

/**
 *
 * @author farid
 */
public class PrintService {
    
    
    public void TestCard(int x){
     try {
             String TestQuery1=""+
"Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd,"+
"Room_Code FROM  Resident_Gl,Student_Res,Branch_Study,Room Where Resident_Gl.NumCard_Resident="+x+" AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd AND Student_Res.Id_Room=Room.Id_Room;";
                      
              JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/NewReportCard.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery1);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,null,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
