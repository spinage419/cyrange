package com.cyrange.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.io.IOException;

//public class KubernetesConfig {
//    // 返回client,默认使用这个方法
//    public static ApiClient defaultClient() throws IOException {
//
//        // IP：默认端口
//        String master = "https://192.168.1.101:6443";
//        // 会很长
//        String oauthToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImpBQmhleU9ZT09aSnVBa185MjNWUnFHc0UxU2R1REpPeU83TDZ2cDNCUHMifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjoxNzM3ODgzNTYwLCJpYXQiOjE3Mzc4Nzk5NjAsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsInNlcnZpY2VhY2NvdW50Ijp7Im5hbWUiOiJhZG1pbi11c2VyIiwidWlkIjoiNGRlN2I5MGUtMDI2Zi00MGM4LWE0YzQtYzI1Y2VkMTM0OGRlIn19LCJuYmYiOjE3Mzc4Nzk5NjAsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlcm5ldGVzLWRhc2hib2FyZDphZG1pbi11c2VyIn0.ci2bio6e57o_fMQ1ddKiDhoaAl6bENVyN3GGdUmHHPAvHacdLiPFuAL-ZrZFxtLbkiaqoJZVGBc0RXeMoljWEW9QfNVcxsW3MhtvSyWSxlYHA1YJIisHf5SUYbEf4mLtJ1-SJxPgKLimZnPgpeN1h-zKSfCba1h_fPQxPqIe552vQVlcZS5c1SuZP3543b83G0kkDuQh7ZgHe-4DwAPmQz4xB5U5so7bdEGp2afKmQvmwdyom6h6gYqNghnLHMoSo4YG-MNvDMMefsLvCb4jxevLL7wETUWOUyCTiZMeekllNDcObMIUA4To_NAbCmTzebmBR8T2wsk--rJnSgOepw";
//
//        ApiClient apiClient = new ClientBuilder()
//                //设置 k8s 服务所在 ip地址
//                .setBasePath(master)
//                //是否开启 ssl 验证
//                .setVerifyingSsl(false)
//                //插入访问 连接用的 Token
//                .setAuthentication(new AccessTokenAuthentication(oauthToken))
//                .build();
//        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(apiClient);
//        return apiClient;
//    }
//}
public class KubernetesConfig {
    public static ApiClient defaultClient()  {
        String master = "https://192.168.1.101:6443";
        String permanentToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImpBQmhleU9ZT09aSnVBa185MjNWUnFHc0UxU2R1REpPeU83TDZ2cDNCUHMifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJteW5hbWVzcGFjZSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJwZXJtYW5lbnQtdG9rZW4iLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiYWRtaW4tdXNlciIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImVlMjc4YzQ5LTVhOTItNDQwZS05ZjVkLTk1ZDQ3MmQ1YjRjZCIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpteW5hbWVzcGFjZTphZG1pbi11c2VyIn0.jxxzmTGpt7Na1wWWbqST6Y7r6GJ0MD90MN-WAclVWrSsF_SMyXGQq9yR5ocYNpBqoSz8OJxTCoeHJXS7vVkZ9SYuaeNWiV0f3fW1Cf6Zv2V0T0ebY7mgkkd1X3xOY9GeXCVRRw4MF3VF6xt8UPf0IQKAafltABA0SH9BW_dE5IfXof6MzoCSO_1Ji_ffUAZf_9lM-x8y90sk2S6Cd8yVzO_DX--yKr0h-bleyHYDuFEqsl--9xvLYBaPLSYTWI3mM4t07GMJm6yp2UzpUk0P1fWF2EP0HUQGa28Cb4C0QcZ8YReHmLRUPh8Ep3YTOikkaB-0bcY5MF0Mt71V_8FvIA";

        ApiClient client = new ClientBuilder()
                .setBasePath(master)
                .setVerifyingSsl(false)
                .setAuthentication(new AccessTokenAuthentication(permanentToken))
                .build();

        Configuration.setDefaultApiClient(client);
        return client;
    }
}
