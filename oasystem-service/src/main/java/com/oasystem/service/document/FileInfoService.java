package com.oasystem.service.document;

import com.oasystem.ResultDTO;
import com.oasystem.model.FileInfo;
import com.oasystem.model.dto.FileQueryDTO;
import com.oasystem.model.vo.FileInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName FileInfoService
 * @Description
 * @Author suguoming
 * @Date 2020/2/7 3:09 下午
 */
public interface FileInfoService {

    /**
     * 查询该部门下的所有文档
     *
     * @param groupId
     * @return
     */
    List<FileInfo> queryAll(Integer groupId);

    /**
     * 新增文档
     *
     * @param fileInfo
     * @return
     */
    ResultDTO<Integer> addFile(FileInfo fileInfo);


    /**
     * 修改文档
     *
     * @param fileInfo
     * @return
     */
    ResultDTO<Integer> updateFile(FileInfo fileInfo);

    /**
     * 删除文档
     *
     * @param fileId
     * @return
     */
    ResultDTO<Integer> deleteFile(List<Integer> fileId, Integer isDelete);

    /**
     * 查询文档
     *
     * @param fileQueryDTO
     * @return
     */
    ResultDTO<List<FileInfoVo>> queryFile(FileQueryDTO fileQueryDTO);

    /**
     * 获取所有的文件夹
     * @param fileQueryDTO
     * @return
     */
    ResultDTO<List<FileInfoVo>> getAllFolders(FileQueryDTO fileQueryDTO);

    /**
     * 上传文件
     * @param file
     * @return
     */
    Map upload(MultipartFile file);

    /**
     * 移动文件
     * @param id
     * @param pid
     * @return
     */
    ResultDTO<Integer> moveFile(Integer id, Integer pid);
}
