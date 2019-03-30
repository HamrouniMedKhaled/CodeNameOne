/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Enseigne;
import com.bonplan.entities.Evenement;
import com.bonplan.services.EnseigneService;
import com.bonplan.services.EventService;
import com.bonplan.util.Vars;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Hamrouni
 */
public class StateForm {
    Form Stat;
 
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLabelsColor(0x000000);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
      CategorySeries series = new CategorySeries(title);
      try{  
      
        EventService eventService=new EventService();
        EnseigneService enseigneService=new EnseigneService();
        ArrayList<Evenement> listEvent = eventService.getAll();
        ArrayList<Enseigne> listEnseigne = enseigneService.getList();
 
Iterator<Enseigne> it = listEnseigne.iterator();
 
while (it.hasNext()) {
    Enseigne ens=it.next();
 long nb =0;
    System.out.println(ens.getId()+"zezeze");
        for( Evenement ee : listEvent)
        {
            
            System.out.println("e"+ee.getEnseigneId().getId()+"rfe"+ens.getId());
          if((int)ee.getEnseigneId().getId() == ens.getId())
                nb++;
        }
        series.add(it.next().getNom(),nb ); 

}
   /* for (Enseigne en :listEnseigne)
    {
        //listEvent.stream().forEach(e->System.out.println(e.getEnseigneId().getId()));
       

    }*/
      }
      catch(Exception i)
      {
          i.printStackTrace();
      }



        return series;
    }

    public Form createPieChartForm() {
        // Generate the values
        double[] values = new double[]{12, 14, 11};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.MAGENTA};
        DefaultRenderer renderer = buildCategoryRenderer(colors);

        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setGradientStop(0, ColorUtil.YELLOW);
        r.setGradientStop(0, ColorUtil.CYAN);
        r.setGradientStop(0, ColorUtil.MAGENTA);


        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Stat = new Form("Statistique", new BorderLayout());
        Stat.getToolbar().addCommandToSideMenu("Acceuil", null, (u) -> {
            Vars.current_choice = 0;
                AcceuilForm af = new AcceuilForm();
                af.getF().show();
        });
        // Stat.getStyle().setBgColor(0x50d3ed);

        Stat.add(BorderLayout.CENTER, c);


        return Stat;

    }

    public void StatistiqueTest() {

        Stat = createPieChartForm();
         /*  Stat.getToolbar().addCommandToLeftBar("back",null,(evt) -> {
                new EvenementForm().show();
        });*/
        Stat.show();

    }

    public Form getF() {
        return Stat;
    }

    public void setF(Form f) {
        this.Stat = f;
    }

    public StateForm() {
        StatistiqueTest();
    }    

    
}
