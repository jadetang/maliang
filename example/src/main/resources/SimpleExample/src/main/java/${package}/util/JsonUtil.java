package ${package}.util;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author TANG SICHENG
 */
public class JsonUtil {



    private final static Gson gson;
    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PageList.class, new PageListSerialiser());
        gson = gsonBuilder.create();
    }


    public static String toJson(Object o){
        return gson.toJson(o);
    }

    private static class PageListSerialiser implements JsonSerializer<PageList> {


        @Override
        public JsonElement serialize(PageList pageList, Type type, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            Paginator paginator = pageList.getPaginator();
            jsonObject.addProperty("totalCount", paginator.getTotalCount());
            jsonObject.addProperty("totalPages", paginator.getTotalPages());
            jsonObject.addProperty("page", paginator.getPage());

            final JsonElement items = context.serialize(new ArrayList(pageList));
            jsonObject.add("items", items);

            jsonObject.addProperty("startRow", paginator.getStartRow());
            jsonObject.addProperty("endRow", paginator.getEndRow());

            jsonObject.addProperty("offset", paginator.getOffset());

            final JsonArray jsonSlides = new JsonArray();
            for (final Integer slide : paginator.getSlider()) {
                final JsonPrimitive jsonSlide = new JsonPrimitive(slide);
                jsonSlides.add(jsonSlide);
            }
            jsonObject.add("slider", jsonSlides);

            jsonObject.addProperty("prePage", paginator.getPrePage());
            jsonObject.addProperty("nextPage", paginator.getNextPage());

            jsonObject.addProperty("firstPage", paginator.isFirstPage());
            jsonObject.addProperty("hasNextPage", paginator.isHasNextPage());
            jsonObject.addProperty("hasPrePage", paginator.isHasPrePage());
            jsonObject.addProperty("lastPage", paginator.isLastPage());

            return jsonObject;
        }
    }


}
