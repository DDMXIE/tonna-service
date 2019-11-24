package com.tony.tonna.mapper;

import com.tony.tonna.entity.Source;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SourceMapper {
    List<Source> getAllSources();
}
