package com.ares.activiti.controller;

import com.ares.activiti.model.CommonModel;
import com.ares.core.controller.BaseController;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.base.ResultCode;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.utils.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ModelEntityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: yy
 * @date: 2020/08/26
 * @see: com.ares.activiti.controller ModelController.java
 **/
@RestController
@RequestMapping("/model/*")
public class ModelController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(ModelController.class);

    private final String MODEL_ID = "modelId";
    private final String MODEL_NAME = "name";
    private final String MODEL_DESCRIPTION = "description";
    private final String MODEL_REVISION = "revision";

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 获取所有模型
     *
     * @return
     */
    @GetMapping("list")
    public TableDataInfo list(ModelEntityImpl model) {
        startPage();
        ModelQuery modelQuery = repositoryService.createModelQuery();
        if (StringUtils.isNotBlank(model.getName())) {
            modelQuery.modelNameLike("%" + model.getName() + "%");
        }
        modelQuery.orderByCreateTime().desc();
        List<Model> models = modelQuery.list();
        return getDataTable(models);
    }

    /**
     * 编辑模型
     *
     * @param newModel
     * @return
     */
    @PostMapping("edit")
    public Object edit(@RequestBody CommonModel newModel) {
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(MODEL_NAME, newModel.getName());
        modelNode.put(MODEL_DESCRIPTION, newModel.getDesc());
        modelNode.put(MODEL_REVISION, newModel.getRevision() != 1 ? "1" : String.valueOf(newModel.getRevisionNext()));
        if (StringUtils.isEmpty(newModel.getId())) {
            Model model = repositoryService.newModel();
            model.setName(newModel.getName());
            model.setKey(newModel.getKey());
            model.setMetaInfo(modelNode.toString());
            repositoryService.saveModel(model);
            addModelSource(model.getId());
        } else {
            //addModelSource(newModel.getId());
//            ModelEntityImpl model = new ModelEntityImpl();
//            model.setId(newModel.getId());
//            model.setName(newModel.getName());
//            model.setKey(newModel.getKey());
            newModel.setMetaInfo(modelNode.toString());
            repositoryService.saveModel(newModel);
        }
        return BaseResult.success();
    }

    private void addModelSource(String id) {
        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.putPOJO("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 根据Id查询模型
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") String id) {
        Model model = repositoryService.createModelQuery().modelId(id).singleResult();
        return BaseResult.successData(model);
    }

    /**
     * 删除模型
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") String id) {
        repositoryService.deleteModel(id);
        return BaseResult.success();
    }

    /**
     * 获取流程定义json数据
     *
     * @param modelId
     * @return
     */
    @GetMapping(value = "/{modelId}/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        ObjectNode modelNode = null;

        Model model = repositoryService.getModel(modelId);

        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(MODEL_NAME, model.getName());
                }
                modelNode.put(MODEL_ID, model.getId());
                byte[] modelEditorSource = repositoryService.getModelEditorSource(model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(modelEditorSource, StandardCharsets.UTF_8));
                modelNode.putPOJO("model", editorJsonNode);

            } catch (Exception e) {
                log.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return modelNode;
    }

    /**
     * 保存流程定义数据
     */
    @PutMapping(value = "/{modelId}/save")
    public void saveModel(@PathVariable String modelId, @RequestParam("name") String name,
                          @RequestParam("json_xml") String json_xml,
                          @RequestParam("svg_xml") String svg_xml,
                          @RequestParam("description") String description) {
        try {
            Model model = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(MODEL_NAME, name);
            modelJson.put(MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            repositoryService.saveModel(model);

            repositoryService.addModelEditorSource(model.getId(), Objects.requireNonNull(json_xml.getBytes(StandardCharsets.UTF_8)));

            InputStream svgStream = new ByteArrayInputStream(Objects.requireNonNull(svg_xml.getBytes(StandardCharsets.UTF_8)));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();

        } catch (Exception e) {
            log.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
    }

    /**
     * 部署模型
     *
     * @param modelId
     * @return
     */
    @GetMapping("/{modelId}/deployment")
    public Object deploy(@PathVariable("modelId") String modelId) {

        // 获取模型
        Model modelData = repositoryService.getModel(modelId);

        if (modelData == null) {
            return BaseResult.error(ResultCode.NOMODEL.getCode(), ResultCode.NOMODEL.getMsg());
        }

        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return BaseResult.error(ResultCode.NOFLOW.getCode(), ResultCode.NOFLOW.getMsg());
        }

        JsonNode modelNode = null;
        try {
            modelNode = new ObjectMapper().readTree(bytes);
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            if (model.getProcesses().size() == 0) {
                return BaseResult.error(ResultCode.ERRORFLOWDEFINITION.getCode(), ResultCode.ERRORFLOWDEFINITION.getMsg());
            }
            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            //发布流程
            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment()
                    .name(modelData.getName())
                    .key(modelData.getKey())
                    .category(modelData.getCategory())
                    .addString(processName, new String(bpmnBytes, StandardCharsets.UTF_8))
                    .deploy();
            modelData.setDeploymentId(deployment.getId());
            repositoryService.saveModel(modelData);
        } catch (IOException e) {
            log.error("Error deployment:", e);
        }
        return BaseResult.success();
    }


}
