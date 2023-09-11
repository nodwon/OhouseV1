package com.portfolio.ohousev1.dto.post.request;

public record PostsUpdateDto(
        String title,
        String content

) {
   public static PostsUpdateDto of(String title, String content){
      return new PostsUpdateDto(title, content);
   }


}
