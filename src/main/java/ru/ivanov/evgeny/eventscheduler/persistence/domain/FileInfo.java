package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.UUIDEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "FILE_INFO")
@AttributeOverride(name = "uuid", column = @Column(name = "FILE_INFO_ID"))
public class FileInfo extends UUIDEntity {

    @Column(name = "FILENAME", nullable = false)
    private String filename;

    @Column(name = "CONTENT_TYPE", nullable = false)
    private String contentType;

    @Column(name = "SIZE", nullable = false)
    private Long size;

    @Column(name = "PATH", length = 2048)
    private String path;

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
