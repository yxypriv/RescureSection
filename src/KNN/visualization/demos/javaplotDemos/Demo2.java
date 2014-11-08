package KNN.visualization.demos.javaplotDemos;

/* Copyright (c) 2007-2014 by panayotis.com
 *
 * JavaPlot is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * JavaPlot is free in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import com.panayotis.gnuplot.JavaPlot;

/**
 * This Object is used to demonstrate JavaPlot library
 *
 * @author teras
 */
public class Demo2 {

    /**
     * @param args the command line arguments. First argument is the path of
     * gnuplot application
     */
    public static void main(String[] args) {
        String path = null;
        if (args.length > 0)
            path = args[0];
        path = "D:/Program Files/gnuplot/bin/gnuplot.exe";
        simple3D(path);
//        simple3D(path);
    }

    private static void simple3D(String path) {
        JavaPlot p = new JavaPlot(path);
        p.newGraph3D();
        p.addPlot("sin(x)*y");
        p.plot();
    }
}
