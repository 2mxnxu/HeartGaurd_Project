package HeartGuard.Aed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AedService {

//    public void test() {
//        try {
//            int totalCount = 3501; // 인천 데이터 전체 개수
//            int numOfRows = 100;
//            int startIndex = 0;
//
//            List<Map<String, Object>> totalItemList = new ArrayList<>();
//
//            while (startIndex < totalCount) {
//                int currentPage = (startIndex / numOfRows) + 1;
//
//                StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B552657/AEDInfoInqireService/getAedLcinfoInqire");
//                urlBuilder.append("?serviceKey=PfP4NaYBNGI4RYJHezHHQDZ1rlFSvElzeBYyGJIQGlh0Wk4kYnIoBuG8%2FxLdWTULAN%2FuKMP2tgq8kw%2BwotNT0w%3D%3D");
//                String 지역조건 = URLEncoder.encode("인천광역시", "UTF-8");
//                urlBuilder.append("&Q0=").append(지역조건);
//                urlBuilder.append("&numOfRows=").append(numOfRows);
//                urlBuilder.append("&pageNo=").append(currentPage);
//
//                URL url = new URL(urlBuilder.toString());
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setRequestProperty("Content-type", "application/xml");
//
//                BufferedReader rd = (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
//                        ? new BufferedReader(new InputStreamReader(conn.getInputStream()))
//                        : new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//
//                StringBuilder sb = new StringBuilder();
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                XmlMapper xmlMapper = new XmlMapper();
//                Map<String, Object> xmlMap = xmlMapper.readValue(sb.toString(), Map.class);
//
//
//                Map<String, Object> body = (Map<String, Object>) xmlMap.get("body");
//                Map<String, Object> items = (Map<String, Object>) body.get("items");
//
//                Object itemObj = items.get("item");
//
//                if (itemObj instanceof List) {
//                    totalItemList.addAll((List<Map<String, Object>>) itemObj);
//                } else if (itemObj instanceof Map) {
//                    totalItemList.add((Map<String, Object>) itemObj);
//                }
//
//                rd.close();
//                conn.disconnect();
//
//                startIndex += numOfRows;
//            }
//
//            // 전체 데이터 출력
//            for (Map<String, Object> item : totalItemList) {
//                System.out.println(item);
//            }
//
//            System.out.println("총 AED 데이터 수집 개수: " + totalItemList.size());
//
//        } catch (Exception e) {
//            System.out.println("에러 발생: " + e.getMessage());
//        }
//    }
public List<Map<String, Object>> getAedInfo() {
    try {
        int totalCount = 3501; // 인천 데이터 전체 개수
        int numOfRows = 100;
        int startIndex = 0;

        List<Map<String, Object>> totalItemList = new ArrayList<>();

        while (startIndex < totalCount) {
            int currentPage = (startIndex / numOfRows) + 1;

            StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B552657/AEDInfoInqireService/getAedLcinfoInqire");
            urlBuilder.append("?serviceKey=PfP4NaYBNGI4RYJHezHHQDZ1rlFSvElzeBYyGJIQGlh0Wk4kYnIoBuG8%2FxLdWTULAN%2FuKMP2tgq8kw%2BwotNT0w%3D%3D");
            String 지역조건 = URLEncoder.encode("인천광역시", "UTF-8");
            urlBuilder.append("&Q0=").append(지역조건);
            urlBuilder.append("&numOfRows=").append(numOfRows);
            urlBuilder.append("&pageNo=").append(currentPage);

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");

            BufferedReader rd = (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
                    ? new BufferedReader(new InputStreamReader(conn.getInputStream()))
                    : new BufferedReader(new InputStreamReader(conn.getErrorStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            XmlMapper xmlMapper = new XmlMapper();
            Map<String, Object> xmlMap = xmlMapper.readValue(sb.toString(), Map.class);

            Map<String, Object> body = (Map<String, Object>) xmlMap.get("body");
            Map<String, Object> items = (Map<String, Object>) body.get("items");

            Object itemObj = items.get("item");

            if (itemObj instanceof List) {
                totalItemList.addAll((List<Map<String, Object>>) itemObj);
            } else if (itemObj instanceof Map) {
                totalItemList.add((Map<String, Object>) itemObj);
            }

            rd.close();
            conn.disconnect();

            startIndex += numOfRows;
        }

        return totalItemList;

    } catch (Exception e) {
        System.out.println("에러 발생: " + e.getMessage());
        return Collections.emptyList(); // 오류가 발생한 경우 빈 리스트 반환
    }

}

    public Map<String, Object> test2() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15081393/v1/uddi:7a3d6102-27dd-4e1c-8dff-f424bd2d5f95");
            urlBuilder.append("?page=1&perPage=21");
            urlBuilder.append("&serviceKey=PfP4NaYBNGI4RYJHezHHQDZ1rlFSvElzeBYyGJIQGlh0Wk4kYnIoBuG8%2FxLdWTULAN%2FuKMP2tgq8kw%2BwotNT0w%3D%3D");

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd = (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
                    ? new BufferedReader(new InputStreamReader(conn.getInputStream()))
                    : new BufferedReader(new InputStreamReader(conn.getErrorStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            resultMap = objectMapper.readValue(sb.toString(), Map.class);

            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("에러 발생: " + e);
        }

        return resultMap;
    }

}

