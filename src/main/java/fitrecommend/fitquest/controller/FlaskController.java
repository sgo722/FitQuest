//package jpabook.jpashop.controller.flask;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.http.HttpHeaders;
//
//public class FlaskController {
//    private RestTemplate restTemplate = new RestTemplate();
//
//    public void sendDataToFlask() {
//        String flaskUrl = "http://flask-server-url/api/endpoint";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // 데이터를 담은 객체를 생성
//        MyData data = new MyData("Hello, Flask!");
//
//        HttpEntity<MyData> request = new HttpEntity<>(data, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                flaskUrl,
//                HttpMethod.POST,
//                request,
//                String.class
//        );
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            // 요청이 성공적으로 처리됨
//            String responseBody = response.getBody();
//            // 플라스크에서 반환한 응답 데이터를 처리
//        } else {
//            // 요청이 실패함
//            // 적절한 예외 처리를 수행
//        }
//    }
//}
