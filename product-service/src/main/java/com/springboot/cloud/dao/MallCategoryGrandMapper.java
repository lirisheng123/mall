package com.springboot.cloud.dao;



import com.springboot.cloud.entity.MallCategoryGrand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MallCategoryGrandMapper {
    int deleteByPrimaryKey(Long categoryGrandId);

    @Delete("DELETE  FROM  mall_category_grand WHERE  category_id= #{categoryId} ")
    int deleteByCategoryId(Long categoryId);

    int insert(MallCategoryGrand record);

   int insertAll(List<MallCategoryGrand> mallCategoryGrands) ;

    MallCategoryGrand selectByPrimaryKey(Long categoryGrandId);

    @Select("SELECT  category_grand_id from mall_category_grand WHERE  grand_id = #{grandId} Limit 1 ")
    Long selectByGrandId(Long grandId);

    @Select("SELECT  * from mall_category_grand WHERE  category_id = #{categoryId} ")
    List<MallCategoryGrand> selectByCategoryId(Long categoryId);

    int updateByPrimaryKey(MallCategoryGrand record);
}