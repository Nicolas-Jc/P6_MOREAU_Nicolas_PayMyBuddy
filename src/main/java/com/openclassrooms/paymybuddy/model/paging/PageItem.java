package com.openclassrooms.paymybuddy.model.paging;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageItem {

    private PageItemType pageItemType;
    private int index;
    private boolean active;

}