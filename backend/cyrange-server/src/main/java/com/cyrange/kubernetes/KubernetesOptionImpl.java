package com.cyrange.kubernetes;
import com.cyrange.config.KubernetesConfig;
import com.google.common.io.Files;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.NetworkingV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Yaml;
import io.kubernetes.client.util.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


@Component
public class KubernetesOptionImpl implements KubernetesOption{
    private ApiClient client;
    private final AppsV1Api appsV1Api;
    private CoreV1Api coreV1Api;
    private NetworkingV1Api networkingV1Api;


    public KubernetesOptionImpl() throws IOException {
        // 初始化客户端之前先注册模型
        registerKubernetesModels();
        this.client = KubernetesConfig.defaultClient();
        Configuration.setDefaultApiClient(this.client);
        this.appsV1Api = new AppsV1Api(this.client);
        this.coreV1Api=new CoreV1Api(this.client);
        this.networkingV1Api=new NetworkingV1Api(this.client);
    }

    /**
     * 显式注册 Kubernetes 资源模型
     */
    private void registerKubernetesModels() {
        try {
            // 注册核心 V1 模型
            ModelMapper.addModelMap("v1", "ConfigMap", V1ConfigMap.class);
            ModelMapper.addModelMap("v1", "Service", V1Service.class);
            ModelMapper.addModelMap("v1", "Pod", V1Pod.class);

            // 注册 Apps/V1 模型
            ModelMapper.addModelMap("apps/v1", "Deployment", V1Deployment.class);
            ModelMapper.addModelMap("apps/v1", "StatefulSet", V1StatefulSet.class);

            // 注册 Networking/V1 模型
            ModelMapper.addModelMap("networking.k8s.io/v1", "Ingress", V1Ingress.class);

            System.out.println("Kubernetes 模型注册完成");
        } catch (Exception e) {
            throw new RuntimeException("无法注册 Kubernetes 模型", e);
        }
    }
    /**
     * 创建Deployment
     * @param yamlFile
     * @throws ApiException
     */
    public void createDeploymentFromYaml(File yamlFile) throws  ApiException {
        Map<String, String> messages = new HashMap<>();
        // 读取YAML文件内容
        try {
            V1Deployment deployment = (V1Deployment) Yaml.load(yamlFile);
            if (deployment != null) {
                V1Deployment createdDeployment = appsV1Api.createNamespacedDeployment(
                        "mynamespace", // 替换为您的命名空间
                        deployment,
                        null, // prettyPrint 参数，通常不需要
                        null, // dryRun 参数，通常不需要
                        null,  // fieldManager 参数，通常不需要
                        null
                        );

                // 如果需要，可以处理返回的createdDeployment对象
                System.out.println("Deployment created: " + createdDeployment.getMetadata().getName());
            } else {
                throw new IOException("Failed to load Deployment from YAML file.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            System.out.println( e.getCode()+e.getMessage());

            if (e.getCode() == 409) {
                messages.put("error", "工作负载创建已重复！");
            } else if (e.getCode() == 200) {
                messages.put("success", "工作负载创建成功！");
            } else if (e.getCode() == 201) {
                messages.put("error", "工作负载创建已重复！");
            } else if (e.getCode() == 401) {

                messages.put("error", "无权限操作！");
            } else {
                messages.put("error", "工作负载创建失败！");
            }
        }
    }


    /**
     * 创建Service
     * @param yamlFile
     * @throws ApiException
     */
    public void createServiceFromYaml(File yamlFile) throws  ApiException {
        Map<String, String> messages = new HashMap<>();
        try {
            V1Service service = (V1Service) Yaml.load(yamlFile);
            if (service != null) {
                V1Service createdService = coreV1Api.createNamespacedService(
                        "mynamespace", // 替换为您的命名空间
                        service,
                        null, // prettyPrint 参数，通常不需要
                        null, // dryRun 参数，通常不需要
                        null,  // fieldManager 参数，通常不需要
                        null
                );

                // 如果需要，可以处理返回的 createdService 对象
                System.out.println("Service created: " + createdService.getMetadata().getName());
            } else {
                throw new IOException("Failed to load Service from YAML file.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            System.out.println(e.getCode() + e.getMessage());

            if (e.getCode() == 409) {
                messages.put("error", "Service 创建已重复！");
            } else if (e.getCode() == 200 || e.getCode() == 201) {
                messages.put("success", "Service 创建成功！");
            } else if (e.getCode() == 401) {
                messages.put("error", "无权限操作！");
            } else {
                messages.put("error", "Service 创建失败！");
            }

            // 可以在这里处理 messages 字典中的消息
            for (Map.Entry<String, String> entry : messages.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    /**
     * 创建Ingress
     * @param yamlFile
     * @throws IOException
     * @throws ApiException
     */
    public void createIngressFromYaml(File yamlFile) throws IOException, ApiException {
        Map<String, String> messages = new HashMap<>();
        try {

            V1Ingress ingress = (V1Ingress)Yaml.load(yamlFile);
            if (ingress != null) {
                V1Ingress createdIngress = networkingV1Api.createNamespacedIngress(
                        "mynamespace", // 替换为您的命名空间
                        ingress,
                        null, // prettyPrint 参数，通常不需要
                        null, // dryRun 参数，通常不需要
                        null,  // fieldManager 参数，通常不需要
                        null
                );

                // 如果需要，可以处理返回的 createdIngress 对象
                System.out.println("Ingress created: " + createdIngress.getMetadata().getName());
            } else {
                throw new IOException("Failed to load Ingress from YAML file.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            System.out.println(e.getCode() + e.getMessage());

            if (e.getCode() == 409) {
                messages.put("error", "Ingress 创建已重复！");
            } else if (e.getCode() == 200 || e.getCode() == 201) {
                messages.put("success", "Ingress 创建成功！");
            } else if (e.getCode() == 401) {
                messages.put("error", "无权限操作！");
            } else {
                messages.put("error", "Ingress 创建失败！");
            }

            // 可以在这里处理 messages 字典中的消息
            for (Map.Entry<String, String> entry : messages.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    @Override
    public void createConfigMapFromYaml(File yamlFile) {
        V1ConfigMap configMap = null;
        try {
            configMap = (V1ConfigMap) Yaml.load(yamlFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            V1ConfigMap createdConfigMap = coreV1Api.createNamespacedConfigMap("mynamespace", configMap, null, null, null,null);
            System.out.println("ConfigMap created: " + createdConfigMap.getMetadata().getName());
        } catch (ApiException e) {
            System.err.println("Error creating ConfigMap: " + e.getMessage());
            System.err.println("HTTP status code: " + e.getCode());
            if (e.getResponseBody() != null) {
                System.err.println("Response body: " + e.getResponseBody());
            }
            throw new RuntimeException(e); // 或者根据需要抛出更具体的异常
        }
    }

    /**
     * 删除Deployment
     * @param deployName
     */
    public void deleteDeployment(String deployName) {

        Map<String, String> messages = new HashMap<>();
        try {
            V1Status v1Status = appsV1Api.deleteNamespacedDeployment(deployName, "mynamespace", null, null, null, null, null, null);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                messages.put("error", "工作负载删除失败，请检查参数是否正确！");
            } else if (e.getCode() == 409) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 200) {
                messages.put("success", "工作负载删除成功！");
            } else if (e.getCode() == 201) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 401) {
                messages.put("error", "无权限操作！");
            } else {
                messages.put("error", "工作负载删除失败！");
            }
        }

    }

    /**
     * 删除service
     * @param serviceName
     */
    public void deleteService(String serviceName) {

        Map<String, String> messages = new HashMap<>();
        try {
            V1Service v1Status = coreV1Api.deleteNamespacedService(serviceName, "mynamespace", null, null, null, null, null, null);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                messages.put("error", "工作负载删除失败，请检查参数是否正确！");
            } else if (e.getCode() == 409) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 200) {
                messages.put("success", "工作负载删除成功！");
            } else if (e.getCode() == 201) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 401) {
                messages.put("error", "无权限操作！");
            } else {
                messages.put("error", "工作负载删除失败！");
            }
        }
    }

    /**
     * 删除ingress
     * @param ingressName
     */
    public void deleteIngress(String ingressName) {

        Map<String, String> messages = new HashMap<>();
        try {
            V1Status v1Status = networkingV1Api.deleteNamespacedIngress(ingressName, "mynamespace", null, null, null, null, null, null);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                messages.put("error", "工作负载删除失败，请检查参数是否正确！");
            } else if (e.getCode() == 409) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 200) {
                messages.put("success", "工作负载删除成功！");
            } else if (e.getCode() == 201) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 401) {
                messages.put("error", "无权限操作！");
            } else {
                messages.put("error", "工作负载删除失败！");
            }
        }
    }


    /**
     * 删除configmap
     * @param configmapName
     */
    public void deleteConfigMap(String configmapName) {

        Map<String, String> messages = new HashMap<>();
        try {
            V1Status v1Status =  coreV1Api.deleteNamespacedConfigMap(configmapName, "mynamespace", null, null, null, null, null, null);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                messages.put("error", "工作负载删除失败，请检查参数是否正确！");
            } else if (e.getCode() == 409) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 200) {
                messages.put("success", "工作负载删除成功！");
            } else if (e.getCode() == 201) {
                messages.put("error", "工作负载删除已重复！");
            } else if (e.getCode() == 401) {
                messages.put("error", "无权限操作！");
            } else {
                messages.put("error", "工作负载删除失败！");
            }
        }
    }


    /**
     * 载入url文件
     * @param fileUrl
     * @return
     */
    public File loadUrlFile(String fileUrl) {

        try {
            URL url = new URL(fileUrl);
            //读到 URL连接对象
            URLConnection conn = url.openConnection();
             //读取 InputStream
            InputStream inStream = conn.getInputStream();

            //new 个 inStream 能支持到 最大的数组
            byte[] buffer = new byte[inStream.available()];
            //一下 全都放到 buffer
            inStream.read(buffer);
            //这里用 google的  Guava ，转成 file
            //File targetFile = new File("D:\\temp\\b.zip");
            File targetFile = new File("/k8syaml/b.zip");
            Files.write(buffer, targetFile);
            return targetFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File updateDeploymentFile(File deploymentfile, String filename) {

        String content=null;
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(new FileInputStream(deploymentfile),"UTF-8" );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (true) {
                try {
                    if (!(-1 != (n = reader.read(buffer)))) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                writer.write(buffer, 0, n);
            }
            content=writer.toString();
            // 读取并更新YAML文件内容
            content = content.replace("cyrange-deployment", "deployment-"+filename);
            content = content.replace("cyrange-config", "configmap-"+filename);
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //File distFile = new File("D:\\temp\\a.zip");
            File distFile = new File("/k8syaml/a.zip");
            bufferedReader = new BufferedReader(new StringReader(content));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            String len;
            while ((len = bufferedReader.readLine()) != null) {
                // 这里因为 单引号 ' 有些问题, 做了下面特殊处理
                len = len.replace("&apos;", "'");
                bufferedWriter.write(len);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
            return distFile;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public File updateServiceFile(File servicefile, String filename) {
        String content=null;
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(new FileInputStream(servicefile),"UTF-8" );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //将输入流写入输出流
        char[] buffer = new char[1024];
        int n = 0;
        while (true) {
            try {
                if (!(-1 != (n = reader.read(buffer)))) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.write(buffer, 0, n);
        }
        content=writer.toString();
        // 读取并更新YAML文件内容
        content = content.replace("cyrange-service", "service-"+filename);
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //File distFile = new File("D:\\temp\\b.zip");
            File distFile = new File("/k8syaml/b.zip");
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(content));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            String len;
            while ((len = bufferedReader.readLine()) != null) {
                // 这里因为 单引号 ' 有些问题, 做了下面特殊处理
                len = len.replace("&apos;", "'");
                bufferedWriter.write(len);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
            return distFile;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public File updateIngressFile(File ingressfile, String filename,String url) {
        String content=null;
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(new FileInputStream(ingressfile),"UTF-8" );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //将输入流写入输出流
        char[] buffer = new char[1024];
        int n = 0;
        while (true) {
            try {
                if (!(-1 != (n = reader.read(buffer)))) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.write(buffer, 0, n);
        }
        content=writer.toString();
        // 读取并更新YAML文件内容
        content = content.replace("cyrange-ingress", "ingress-"+filename);
        content = content.replace("cyrange.xin", url);
        content = content.replace("cyrange-service", "service-"+filename);
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //File distFile = new File("D:\\temp\\c.zip");
            File distFile = new File("/k8syaml/c.zip");
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(content));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            String len;
            while ((len = bufferedReader.readLine()) != null) {
                // 这里因为 单引号 ' 有些问题, 做了下面特殊处理
                len = len.replace("&apos;", "'");
                bufferedWriter.write(len);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
            return distFile;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public File updateConfigMapFile(File configmapfile, String filename,String flag) {
        String content=null;
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(new FileInputStream(configmapfile),"UTF-8" );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //将输入流写入输出流
        char[] buffer = new char[1024];
        int n = 0;
        while (true) {
            try {
                if (!(-1 != (n = reader.read(buffer)))) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.write(buffer, 0, n);
        }
        content=writer.toString();
        // 读取并更新YAML文件内容
        content = content.replace("cyrange-configmap", "configmap-"+filename);
        content = content.replace("cyrange{}", flag);
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //File distFile = new File("D:\\temp\\d.zip");
          File distFile = new File("/k8syaml/d.zip");
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(content));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            String len;
            while ((len = bufferedReader.readLine()) != null) {
                // 这里因为 单引号 ' 有些问题, 做了下面特殊处理
                len = len.replace("&apos;", "'");
                bufferedWriter.write(len);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
            return distFile;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
