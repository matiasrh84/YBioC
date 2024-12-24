/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ybc.ybioq.config;


import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Valida_Online {

    String mensajenuevo = "", num_orden = "", plan_cadena = "", coseguro_cadena = "";
    double coseguro = 0.0;
    String fechahora_medife = "", codigo_seguridad_medife = "";

    /// , fecha_txt = "";

    public String get_num_orden() {
        return num_orden;
    }

    public String get_coseguro() {
        return String.valueOf(coseguro);
    }

    public String get_plancadena() {
        return plan_cadena;
    }

    public String get_cosegurocadena() {
        return coseguro_cadena;
    }

    String completarceros(String v, int d
    ) {
        String ceros = "";
        if (v.length() < d) {
            for (int i = v.length(); i < d; i++) {
                ceros += "0";
            }
            v = ceros + v;
        }
        return v;
    }

    public int Boreal_online(String[] practicas, int cantidad, String emisor, String clave, String cuil, String Numero_afiliado, String tipo_afiliado, String fecha) {
//        int bandera_boreal = 0;
//        System.out.println("boreal 4-online-0 " + cantidad);
//        if (cantidad != 0) {
//            int i = 0;
//            String cod_practca_boreal;
//            while (i < cantidad) {
//                System.out.println("boreal 4-online-1");
//                cod_practca_boreal = practicas[i];
//                if (!cod_practca_boreal.equals("660001")) {
//                    mensajenuevo = mensajenuevo + "<Practica><LineaNro>" + (i + 1) + "</LineaNro><SeccionId>15</SeccionId><PracticaId>" + cod_practca_boreal + "</PracticaId><PracticaItem>5</PracticaItem><PracticaCantSol>1</PracticaCantSol><PracticaCantAprob></PracticaCantAprob><PracticaDes></PracticaDes><PracticaCoseguro></PracticaCoseguro><PracticaIdEstado></PracticaIdEstado><PracticaObs></PracticaObs><PracticaPreAutorizacion></PracticaPreAutorizacion></Practica>";
//                }
//                i++;
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
//        }
//        System.out.println("valida boreal " + mensajenuevo);
//        /////////////////////////////////////////////////////////////////////////////////
//        String mensajepractica = "<BOREAL><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Empresa>BOREAL</Empresa><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z02</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo><MsgEntorno>P</MsgEntorno></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuil + "</PrestadorId><PrestadorNombre>colegiobioquimico</PrestadorNombre><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Afiliado><AfiliadoNroCredencial>" + Numero_afiliado + "</AfiliadoNroCredencial><AfiliadoGf>" + tipo_afiliado + "</AfiliadoGf><TipoIdentificador>HC</TipoIdentificador></Afiliado><Practicas>" + mensajenuevo + "</Practicas></BOREAL>";
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("Testing 1 - Send Http GET request");
//        System.out.println(mensajepractica);
//        ///////////////////////////////////////////////////////////////////////////////////////////////
//        ClienteBoreal.WsBorealExecute servicio_boreal = new ClienteBoreal.WsBorealExecute();
//        servicio_boreal.setIngresoxml(mensajepractica);
//        String respuestapractica = execute(servicio_boreal).getEgresoxml();
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("Testing 2 - Get Http GET request");
//        System.out.println(respuestapractica);
//        ////////////////////////////////////////////////////////////////////////////////////////////////
//        int pos = respuestapractica.indexOf("<AutEstadoId>");
//        int pos2 = respuestapractica.indexOf("</AutEstadoId>");
//        ///JOptionPane.showMessageDialog(null, respuestapractica.substring(pos + 13, pos2));
//
//        if (respuestapractica.substring(pos + 13, pos2).equals("B000")) {
//            DecimalFormat df = new DecimalFormat("0.00");
//            LinkedList<camposboreal> Resultados = new LinkedList<camposboreal>();
//            Resultados.clear();
//            try {
//                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//                InputSource is = new InputSource();
//                is.setCharacterStream(new StringReader(respuestapractica));
//                Document doc = db.parse(is);
//                NodeList nodes = doc.getElementsByTagName("Practica");
//                int pos5 = respuestapractica.indexOf("<AutCod>");
//                int pos6 = respuestapractica.indexOf("</AutCod>");
//                num_orden = respuestapractica.substring(pos5 + 8, pos6);
//                String codigo = "", coseguroboreal = "";
//                for (int w4 = 0; w4 < nodes.getLength(); w4++) {
//                    Element element = (Element) nodes.item(w4);
//                    NodeList name = element.getElementsByTagName("PracticaId");
//                    Element line = (Element) name.item(0);
//                    System.out.println("PracticaId: " + getCharacterDataFromElement(line));
//                    codigo = getCharacterDataFromElement(line);
//                    NodeList title = element.getElementsByTagName("PracticaCoseguro");
//                    line = (Element) title.item(0);
//                    System.out.println(getCharacterDataFromElement(line));
//                    coseguro = coseguro + Double.valueOf(getCharacterDataFromElement(line));
//                    coseguroboreal = "$ " + getCharacterDataFromElement(line);
//                    /////////////////////////////////////////////////////////
//                    plan_cadena = plan_cadena + "00";
//                    coseguro_cadena = coseguro_cadena + completarceros(String.valueOf(coseguro), 7);
//                    ////////////////////////////////////////////////////////
//                    camposboreal tipo;
//                    tipo = new camposboreal(codigo, coseguroboreal);
//                    Resultados.add(tipo);
//                    //////////////////////////////////////////////////////////
//                }
//
//                int opcion = JOptionPane.showConfirmDialog(null, "Coseguro: $" + coseguro + "\nDesea Imprimir un comprobante?", "Boreal Impresíon", JOptionPane.YES_NO_OPTION);
//                if (opcion == 0) {
//                    try {
//                        JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Comprobante_boreal.jasper"));
//                        ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
//                        Map parametros = new HashMap();
//                        parametros.put("numero_autorizacion", num_orden);
//                        parametros.put("fecha", fecha);
//                        parametros.put("nombre", respuestapractica.substring(respuestapractica.indexOf("<AfiliadoNombre>") + 16, respuestapractica.indexOf("</AfiliadoNombre>")));
//                        parametros.put("numero", Numero_afiliado + "/" + tipo_afiliado);
//                        parametros.put("total", df.format(coseguro));
//                        JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
//                        JasperPrintManager.printReport(jPrint, false);
//                    } catch (Exception e) {
//                        bandera_boreal = 0;
//                        JOptionPane.showMessageDialog(null, e);
//                    }
//
//                }
//                bandera_boreal = 1;
//            } catch (Exception ex) {
//                bandera_boreal = 0;
//                JOptionPane.showMessageDialog(null, ex);
//            }
//
//            ////
//            //
//            JOptionPane.showMessageDialog(null, "Nro. Autorización: " + num_orden);
//            return bandera_boreal;
//        } else {
//            bandera_boreal = 0;
//            int pos3 = respuestapractica.indexOf("<AutObs>");
//            int pos4 = respuestapractica.indexOf("</AutObs>");
//            try {
//                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//                InputSource is = new InputSource();
//                is.setCharacterStream(new StringReader(respuestapractica));
//                Document doc = db.parse(is);
//                NodeList nodes = doc.getElementsByTagName("Practica");
//                int pos5 = respuestapractica.indexOf("<AutCod>");
//                int pos6 = respuestapractica.indexOf("</AutCod>");
//                num_orden = respuestapractica.substring(pos5 + 8, pos6);
//                String salida = "";
//                for (int w4 = 0; w4 < nodes.getLength(); w4++) {
//                    Element element = (Element) nodes.item(w4);
//                    NodeList name = element.getElementsByTagName("PracticaId");
//                    Element line = (Element) name.item(0);
//                    System.out.println("PracticaId: " + getCharacterDataFromElement(line));
//                    salida = salida + "\t" + getCharacterDataFromElement(line);
//                    NodeList title = element.getElementsByTagName("PracticaObs");
//                    line = (Element) title.item(0);
//                    System.out.println(getCharacterDataFromElement(line));
//                    salida = salida + "      " + getCharacterDataFromElement(line) + "-\n";
//                }
//                JOptionPane.showMessageDialog(null, salida);
//            } catch (Exception e) {
//                bandera_boreal = 0;
//                JOptionPane.showMessageDialog(null, e);
//            }
//            return bandera_boreal;
//        }
        return cantidad; //temporal
    }

    void cargarfechamedife() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formato2 = new SimpleDateFormat("yyMMddHHmmss");
        SimpleDateFormat formato3 = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);

        fechahora_medife = formato.format(currentDate);
        codigo_seguridad_medife = formato2.format(currentDate) + formato3.format(currentDate);
    }

    public int Medife_online(String[] practicas, int cantidad, String emisor, String cuil, String Numero_afiliado, String fecha) {
        int bandera_medife = 0;
        cargarfechamedife();
        num_orden = "";
        String mensaje = "";
        TripleDes tpDatos = new TripleDes();
        /////////////////////////////////////////////////////////////////////////////////

        short cantidad_medife = 1;

        if (cantidad != 0) {
            int i = 0;
            String cod_practica, cadena_PR1 = "";
            while (i < cantidad) {
                System.out.println("boreal 4-online-1");
                cod_practica = practicas[i];
                cadena_PR1 = cadena_PR1
                        + "PR1|" + (i + 1) + "||" + cod_practica + "^^3\r\n"
                        + "AUT||||||||1\r\n"
                        + "ZAU||||||0&$\r\n";
                i++;
            }
            ///////////////////////////////////////////////////////////////////////                        
            String Mensaje = "MSH|^~\\&|TRIA0100M|TRIA00007526|MEDIFE|MEDIFE^222222^IIN|" + fechahora_medife + "||ZQA^Z02^ZQA_Z02|" + codigo_seguridad_medife + "|P|2.4|||NE|AL|ARG\r\n"
                    + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
                    + "PRD|EF^Efector||^^^T||||" + cuil + "^CU&M&C|\r\n"
                    + "PID|||" + Numero_afiliado + "^^^MEDIFE^HC^MEDIFE||UNKNOWN\r\n"
                    + cadena_PR1
                    + "PV1||O||P|||||||||||||||||||||||||||||||||||||||||||||||V";
            /////////////////////////////////////////////////////////////
            String clave = "IA007526";
            String usuario = "IA007526";
            String tipo = "SI";
            String llave = "1234567890123456ABCDEFGH";
            String pszMsg = tpDatos.EncriptarStr(Mensaje, llave);
            ///////////////////////////////////////////////////////////////
//            try {
//                // Call Web Service Operation
//                WebServiceIA service = new WebServiceIA();
//                WebServiceIASoap port = service.getWebServiceIASoap();
//                // TODO initialize WS operation arguments here
//                String pszUser = tpDatos.EncriptarStr(usuario, llave);
//                String pszPwd = tpDatos.EncriptarStr(clave, llave);
//                String pszMsgType = tpDatos.EncriptarStr(tipo, llave);
//
//                // TODO process result here
//                String result = port.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
//                System.out.println("Respuesta = " + result);
//                ///busco la respuesta
//                i = result.indexOf("ZAU");
//                int pipe = 0;
//                while (i < result.indexOf("PRD")) {
//                    if (pipe == 3) {
//                        mensaje = mensaje + result.charAt(i);
//                    }
//                    if (result.charAt(i) == '|') {
//                        pipe++;
//                    }
//                    i++;
//                }
//                ////busco numero de respuesta
//                i = result.indexOf("ZAU");
//                pipe = 0;
//                while (i < result.indexOf("PRD")) {
//                    if (pipe == 2) {
//                        if (result.charAt(i) != '|') {
//                            num_orden = num_orden + result.charAt(i);
//                        }
//                    }
//                    if (result.charAt(i) == '|') {
//                        pipe++;
//                    }
//                    i++;
//                }
//                mensaje = mensaje.replace("^", " ");
//                String codigo_respuesta = mensaje.substring(0, 4);
//                System.out.println(num_orden + " " + mensaje);
//                System.out.println(codigo_respuesta);
//                ////////////////////////////////////////////////////////////////
//                if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
//                    return 1;
//                }else {
//                    System.out.println(mensaje);
//                    JOptionPane.showMessageDialog(null, mensaje);
//                    bandera_medife = 0;
//                    return 0;
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, ex);
//                return 0;
//            }
            return 0;
        } else {
            JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
            return 0;
        }
    }

//    private static WsBorealExecuteResponse execute(ClienteBoreal.WsBorealExecute parameters) {
//        ClienteBoreal.WsBoreal service = new ClienteBoreal.WsBoreal();
//        ClienteBoreal.WsBorealSoapPort port = service.getWsBorealSoapPort();
//        return port.execute(parameters);
//    }
}
