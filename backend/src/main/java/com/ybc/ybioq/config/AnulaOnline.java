package com.ybc.ybioq.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AnulaOnline {

    String mensajenuevo = "", num_orden = "", plan_cadena = "", coseguro_cadena = "", mensajepractica = "", respuestapractica = "";
    double coseguro = 0.0;
    String fechahora_medife = "", codigo_seguridad_medife = "";

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

    public int Boreal_online(String emisor, String clave, String cuil, String num_orden) {
        //////////////////////////////////////////////////////////////
//        System.out.println("4");
//        System.out.println(emisor);
//        System.out.println(cuil);
//        int bandera_boreal = 0;
//        /////////////////////////////////////////////////////////////////////////////////
//        mensajepractica = "<Boreal><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z04</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuil + "</PrestadorId><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Autorizacion><AutCod></AutCod><AutEstadoId></AutEstadoId><AutObs></AutObs><AutCodAnulacion>" + num_orden + "</AutCodAnulacion></Autorizacion></Boreal>";
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("Enviar Anulacion online test 1 ");
//        System.out.println(mensajepractica);
//        ///////////////////////////////////////////////////////////////////////////////////////////////
//        ClienteBoreal.WsBorealExecute servicio = new ClienteBoreal.WsBorealExecute();
//        servicio.setIngresoxml(mensajepractica);
//        respuestapractica = execute(servicio).getEgresoxml();
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("Respuesta Anulacion online test 2 ");
//        System.out.println(respuestapractica);
//        ////////////////////////////////////////////////////////////////////////////////////////////////
//        int pos = respuestapractica.indexOf("<AutEstadoId>");
//        int pos2 = respuestapractica.indexOf("</AutEstadoId>");
//        if (respuestapractica.substring(pos + 13, pos2).equals("B000")) {
//            int pos3 = respuestapractica.indexOf("<AutCod>");
//            int pos4 = respuestapractica.indexOf("</AutCod>");
//            num_orden = respuestapractica.substring(pos3 + 8, pos4);
//            JOptionPane.showMessageDialog(null, "Numero de Anulación:" + num_orden);
//            bandera_boreal = 1;
//        } else {
//            if (respuestapractica.substring(pos + 13, pos2).equals("M054")) {
//                int pos3 = respuestapractica.indexOf("<AutObs>");
//                int pos4 = respuestapractica.indexOf("</AutObs>");
//                JOptionPane.showMessageDialog(null, respuestapractica.substring(pos3 + 8, pos4));
//            }
//            bandera_boreal = 0;
//        }
//        System.out.println("4----");
//        return bandera_boreal;
        return 0;
    }

    public int Medife_online(String Codigo_afiliado, String cuil, String num_orden) {
        num_orden = "";
        String mensaje = "";
        cargarfechamedife();
        System.out.println("Anulacion medife");
        String codigo_respuesta = "";
        String Anulacion = "MSH|^~\\&|TRIA0100M|TRIA00007526|MEDIFE|MEDIFE^222222^IIN|" + fechahora_medife + "||ZQA^Z04^ZQA_Z02|" + codigo_seguridad_medife + "|P|2.4|||NE|AL|ARG\r\n"
                + "ZAU||" + num_orden + "\r\n"
                + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
                + "PRD|EF^Efector||^^^T||||" + cuil + "^CU&M&C|\r\n"
                + "PID|||" + Codigo_afiliado + "^^^MEDIFE^HC^MEDIFE||UNKNOWN";
        String clave = "IA007526";
        String usuario = "IA007526";
        String tipo = "SI";
        String llave = "1234567890123456ABCDEFGH";
        TripleDes tpDatos = new TripleDes();
        String pszMsg = tpDatos.EncriptarStr(Anulacion, llave);
//
//        try { // Call Web Service Operation
//            WebServiceIA service2 = new WebServiceIA();
//            WebServiceIASoap port2 = service2.getWebServiceIASoap();
//            // TODO initialize WS operation arguments here
//            String pszUser = tpDatos.EncriptarStr(usuario, llave);
//            String pszPwd = tpDatos.EncriptarStr(clave, llave);
//            String pszMsgType = tpDatos.EncriptarStr(tipo, llave);
//            // TODO process result here
//            String result = port2.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
//            System.out.println("Respuesta = " + result);
//            ///busco la respuesta
//            int i = result.indexOf("ZAU");
//            int pipe = 0;
//            while (i < result.indexOf("PRD")) {
//                if (pipe == 3) {
//                    mensaje = mensaje + result.charAt(i);
//                }
//                if (result.charAt(i) == '|') {
//                    pipe++;
//                }
//                i++;
//            }
//            ////busco numero de respuesta
//            i = result.indexOf("ZAU");
//            pipe = 0;
//            while (i < result.indexOf("PRD")) {
//                if (pipe == 2) {
//                    num_orden = num_orden + result.charAt(i);
//                }
//                if (result.charAt(i) == '|') {
//                    pipe++;
//                }
//                i++;
//            }
//            mensaje = mensaje.replace("^", " ");
//            codigo_respuesta = mensaje.substring(0, 4);
//            System.out.println(num_orden + " " + mensaje);
//            return 1;
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex);
//            return 0;
//        }
        return 1;
    }

//    private static WsBorealExecuteResponse execute(ClienteBoreal.WsBorealExecute parameters) {
//        ClienteBoreal.WsBoreal service = new ClienteBoreal.WsBoreal();
//        ClienteBoreal.WsBorealSoapPort port = service.getWsBorealSoapPort();
//        return port.execute(parameters);
//    }
}
