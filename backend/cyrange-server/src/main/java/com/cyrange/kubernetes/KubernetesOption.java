package com.cyrange.kubernetes;

import io.kubernetes.client.openapi.ApiException;
import java.io.File;
import java.io.IOException;

public interface KubernetesOption {

    /**
     * 创建Deployment
     * @param yamlFile
     * @throws ApiException
     */
    void createDeploymentFromYaml(File yamlFile) throws ApiException;

    /**
     * 创建Service
     *
     * @param yamlFile
     * @throws ApiException
     */
    void createServiceFromYaml(File yamlFile) throws ApiException;

    /**
     * 创建Ingress
     * @param yamlFile
     * @throws IOException
     * @throws ApiException
     */
    void createIngressFromYaml(File yamlFile) throws IOException, ApiException;

    /**
     * 删除Deployment
     * @param yamlFile
     */

    void createConfigMapFromYaml(File yamlFile);

    /**
     * 删除deployment
     * @param deployName
     */
    void deleteDeployment(String deployName);

    /**
     * 删除service
     * @param serviceName
     */
    void deleteService(String serviceName);

    /**
     * 删除ingress
     * @param ingressName
     */
    void deleteIngress(String ingressName);

    /**
     * 删除configmap
     * @param configmapName
     */
    void deleteConfigMap(String configmapName);
    /**
     * 载入url文件
     * @param fileUrl
     * @return
     */
    File loadUrlFile(String fileUrl);
    File updateDeploymentFile(File deploymentfile, String filename);

    File updateServiceFile(File servicefile, String filename);

    File updateIngressFile(File ingressfile, String filename,String url);

    File updateConfigMapFile(File configmapfile, String filename,String flag);


}
