/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.boostrap.singleton.ConnectionMongodbClient;
import com.mongodb.util.DatabasesAndCollectionsNames;
import com.mongodb.util.JSON;
import com.mongodb.util.MongodbQueryOperationsUtils;
import com.my.librery.MyLibrery;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Marco
 * 
 * ANDARE SU https://www.w3schools.com/bootstrap/bootstrap_examples.asp
 * 
 * 
 * ESERCIZI DA VEDERE ANCORA ON LINE
 * https://www.w3schools.com/bootstrap/bootstrap_grid_examples.asp
 * 
 * 
 */






@MultipartConfig 
@Controller
public class ControllerPagineBootstrap  extends HttpServlet {// INSERITO IL 9 MAGG 2018  extends HttpServlet
    

     

      
 

    
     @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    
     //0style_navbar
    @RequestMapping(value = "/0style_navbar", method = RequestMethod.GET)
    public String style_navbar0() {
        return "0style_navbar";
    }
    
     @RequestMapping(value = "/navbarStyle1", method = RequestMethod.GET)
    public String navbar1() {
        return "1style_navbar";
    }
    
    
    @RequestMapping(value = "/navbarStyle2", method = RequestMethod.GET)
    public String navbar2() {
        return "2style_navbar";
    }
    
    
    @RequestMapping(value = "/navbarStyle3", method = RequestMethod.GET)
    public String navbar3() {
        return "3style_navbar";
    }
    
    
    //1navbar-btn
     @RequestMapping(value = "/1navbar_btn", method = RequestMethod.GET)
    public String navbar_btn1() {
        return "1navbar-btn";
    }
    
    //1non-navbar-links
     @RequestMapping(value = "/1non_navbar_links", method = RequestMethod.GET)
    public String non_navbar_links1() {
        return "1non-navbar-links";
    }
    
    //1dropdowns-menu
     @RequestMapping(value = "/1dropdowns_menu", method = RequestMethod.GET)
    public String dropdowns_menu1() {
        return "1dropdowns-menu";
    }
    
    
     //2dropdowns-menu
     @RequestMapping(value = "/2dropdowns_menu", method = RequestMethod.GET)
    public String dropdowns_menu2() {
        return "2dropdowns-menu";
    }
    
    //1navbar-dropdown-menu
     @RequestMapping(value = "/1navbar_dropdown_menu", method = RequestMethod.GET)
    public String navbar_dropdown_menu1() {
        return "1navbar-dropdown-menu";
    }
    
    
    
    
    
    //1navbar-form
    @RequestMapping(value = "/1navbar_form", method = RequestMethod.GET)
    public String navbar_form1() {
        return "1navbar-form";
    }
    
  
    //4style_navbar
     @RequestMapping(value = "/4style_navbar", method = RequestMethod.GET)
    public String style_navbar4() {
        return "4style_navbar";
    }
    
    //5style_navbar-scroll
     @RequestMapping(value = "/5style_navbar_scroll", method = RequestMethod.GET)
    public String style_navbar_scroll5() {
        return "5style_navbar-scroll";
    }
    
    //6style_navbar-collapse-tendina
     @RequestMapping(value = "/6style_navbar_collapse_tendina", method = RequestMethod.GET)
    public String style_navbar_collapse_tendina6() {
        return "6style_navbar-collapse-tendina";
    }
    
  
    //1acordion
      @RequestMapping(value = "/1acordion", method = RequestMethod.GET)
    public String acordion1() {
        return "1acordion";
    }
    
    
    //1basic-table
    @RequestMapping(value = "/1basic_table", method = RequestMethod.GET)
    public String basic_table1() {
        return "1basic-table";
    }
    
    //    Document   : 1basic-table-zebra
    @RequestMapping(value = "/1basic_table_zebra", method = RequestMethod.GET)
    public String basic_table_zebra1() {
        return "1basic-table-zebra";
    }
    
    
    //1basic-table-bordered
     @RequestMapping(value = "/1basic_table_bordered", method = RequestMethod.GET)
    public String basic_table_bordered1() {
        return "1basic-table-bordered";
    }
    
    
    //1basic-table-hover-rows
     @RequestMapping(value = "/1basic_table_hover_rows", method = RequestMethod.GET)
    public String basic_table_hover_rows1() {
        return "1basic-table-hover-rows";
    }
    
    
    //1basic-table-responsive
     @RequestMapping(value = "/1basic_table_responsive", method = RequestMethod.GET)
    public String basic_table_responsive1() {
        return "1basic-table-responsive";
    }
    
    
    //1rounded-images
     @RequestMapping(value = "/1rounded_images", method = RequestMethod.GET)
    public String rounded_images1() {
        return "1rounded-images";
    }
    
    
    //2images-gallery
     @RequestMapping(value = "/2images_gallery", method = RequestMethod.GET)
    public String images_gallery2() {
        return "2images-gallery";
    }
    
    //3image-responsive
     @RequestMapping(value = "/3image-responsive", method = RequestMethod.GET)
    public String image_responsive3() {
        return "3image-responsive";
    }
    
    
    //1video-responsive-embed
    @RequestMapping(value = "/1video_responsive_embed", method = RequestMethod.GET)
    public String video_responsive_embed1() {
        return "1video-responsive-embed";
    }
    
    
    //1jumbotron
     @RequestMapping(value = "/1jumbotron", method = RequestMethod.GET)
    public String jumbotron1() {
        return "1jumbotron";
    }
    
    //1header
    @RequestMapping(value = "/1header", method = RequestMethod.GET)
    public String header1() {
        return "1header";
    }
    
    //1well-basic
     @RequestMapping(value = "/1well_basic", method = RequestMethod.GET)
    public String well_basic1() {
        return "1well-basic";
    }
    
    
    //2well-size
    @RequestMapping(value = "/2well_size", method = RequestMethod.GET)
    public String well_size2() {
        return "2well-size";
    }
    
    
    //alerts1
    @RequestMapping(value = "/alerts1", method = RequestMethod.GET)
    public String alerts1() {
        return "alerts1";
    }
    
    
    //alerts-clossing2
    @RequestMapping(value = "/alerts_clossing2", method = RequestMethod.GET)
    public String alerts_clossing2() {
        return "alerts-clossing2";
    }
    
    //alerts-animated3
    @RequestMapping(value = "/alerts_animated3", method = RequestMethod.GET)
    public String alerts_animated3() {
        return "alerts-animated3";
    }
    
    
    //button-style1
    @RequestMapping(value = "/button_style1", method = RequestMethod.GET)
    public String button_style1() {
        return "button-style1";
    }
    
    //button-tags-2
    @RequestMapping(value = "/button_tags_2", method = RequestMethod.GET)
    public String button_tags_2() {
        return "button-tags-2";
    }
    
    
    //button-sizes-3
    @RequestMapping(value = "/button_sizes_3", method = RequestMethod.GET)
    public String button_sizes_3() {
        return "button-sizes-3";
    }
    
    //button-level-block4
    @RequestMapping(value = "/button_level_block4", method = RequestMethod.GET)
    public String button_level_block4() {
        return "button-level-block4";
    }
    
    
    //button-states-disabiltati5
    @RequestMapping(value = "/button_states_disabiltati5", method = RequestMethod.GET)
    public String button_states_disabiltati5() {
        return "button-states-disabiltati5";
    }
    
    
    //button-group-6
    @RequestMapping(value = "/button_group_6", method = RequestMethod.GET)
    public String button_group_6() {
        return "button-group-6";
    }
    
    
    //button-group-set-size-7
    @RequestMapping(value = "/button_group_set_size_7", method = RequestMethod.GET)
    public String button_group_set_size_7() {
        return "button-group-set-size-7";
    }
    
    
    //button-group-vertical-8
     @RequestMapping(value = "/button_group_vertical_8", method = RequestMethod.GET)
    public String button_group_vertical_8() {
        return "button-group-vertical-8";
    }
    
    
    //button-group-nested-9
    @RequestMapping(value = "/button_group_nested_9", method = RequestMethod.GET)
    public String button_group_nested_9() {
        return "button-group-nested-9";
    }
    
    //button-split-10
    @RequestMapping(value = "/button_split_10", method = RequestMethod.GET)
    public String button_split_10() {
        return "button-split-10";
    }
    
    
    //glyphycon-1
     @RequestMapping(value = "/glyphycon_1", method = RequestMethod.GET)
    public String glyphycon_1() {
        return "glyphycon-1";
    }
    
    //badges-1
    @RequestMapping(value = "/badges_1", method = RequestMethod.GET)
    public String badges_1() {
        return "badges-1";
    }
    
    
    //badges-inside-button-2
    @RequestMapping(value = "/badges_inside_button_2", method = RequestMethod.GET)
    public String badges_inside_button_2() {
        return "badges-inside-button-2";
    }
    
    
    //pagination-1
    @RequestMapping(value = "/pagination_1", method = RequestMethod.GET)
    public String pagination_1() {
        return "pagination-1";
    }
    
    
    //pagination-active-state-2
    @RequestMapping(value = "/pagination_active_state_2", method = RequestMethod.GET)
    public String pagination_active_state_2() {
        return "pagination-active-state-2";
    }
    
    //pagination-disable-state-3
    @RequestMapping(value = "/pagination_disable_state_3", method = RequestMethod.GET)
    public String pagination_disable_state_3() {
        return "pagination-disable-state-3";
    }
    
    //pagination-sizing-4
    @RequestMapping(value = "/pagination_sizing_4", method = RequestMethod.GET)
    public String pagination_sizing_4() {
        return "pagination-sizing-4";
    }
    
    //breadcrumb-1
    @RequestMapping(value = "/breadcrumb_1", method = RequestMethod.GET)
    public String breadcrumb_1() {
        return "breadcrumb-1";
    }
    
    //pager-1
     @RequestMapping(value = "/pager_1", method = RequestMethod.GET)
    public String pager_1() {
        return "pager-1";
    }
    
    //pager-separator-align-2
    @RequestMapping(value = "/pager_separator_align_2", method = RequestMethod.GET)
    public String pager_separator_align_2() {
        return "pager-separator-align-2";
    }
    
    //list-group-1
    @RequestMapping(value = "/list_group_1", method = RequestMethod.GET)
    public String list_group_1() {
        return "list-group-1";
    }
    
    //list-group-with-badges-1
    @RequestMapping(value = "/list_group_with_badges_1", method = RequestMethod.GET)
    public String list_group_with_badges_1() {
        return "list-group-with-badges-1";
    }
    
    
    //list-group-linked-items-3
    @RequestMapping(value = "/list_group_linked_items_3", method = RequestMethod.GET)
    public String list_group_linked_items_3() {
        return "list-group-linked-items-3";
    }
    
    
    
    //list-inline-1
    @RequestMapping(value = "/list_inline_1", method = RequestMethod.GET)
    public String list_inline_1() {
        return "list-inline-1";
    }
    
    
    //list-inline-tabs-dropdown-menu
    @RequestMapping(value = "/list_inline_tabs_dropdown_menu_2", method = RequestMethod.GET)
    public String list_inline_tabs_dropdown_menu_2() {
        return "list-inline-tabs-dropdown-menu-2";
    }
    
    
    //tabs-dynamic-1
    @RequestMapping(value = "/tabs_dynamic_1", method = RequestMethod.GET)
    public String tabs_dynamic_1() {
        return "tabs-dynamic-1";
    }
    
    //tabs-pills-1
    @RequestMapping(value = "/tabs_pills_1", method = RequestMethod.GET)
    public String tabs_pills_1() {
        return "tabs-pills-1";
    }
    
    //tab-pills-dropdown-menu-2
    @RequestMapping(value = "/tab_pills_dropdown_menu_2", method = RequestMethod.GET)
    public String tab_pills_dropdown_menu_2() {
        return "tab-pills-dropdown-menu-2";
    }
    
    
    //form-basic-1
    @RequestMapping(value = "/form_basic_1", method = RequestMethod.GET)
    public String form_basic_1() {
        return "form-basic-1";
    }
    
    //form-control-radio-button-2
    @RequestMapping(value = "/form_control_radio_button_2", method = RequestMethod.GET)
    public String form_control_radio_button_2() {
        return "form-control-radio-button-2";
    }
    
    //form-control-text-area-3
    @RequestMapping(value = "/form_control_text_area_3", method = RequestMethod.GET)
    public String form_control_text_area_3() {
        return "form-control-text-area-3";
    }
    
    //form-control-select-4
    @RequestMapping(value = "/form_control_select_4", method = RequestMethod.GET)
    public String form_control_select_4() {
        return "form-control-select-4";
    }
    
    //input-sizing-1
    @RequestMapping(value = "/input_sizing_1", method = RequestMethod.GET)
    public String input_sizing_1() {
        return "input-sizing-1";
    }
    
    
    //media-object-1
    @RequestMapping(value = "/media_object_1", method = RequestMethod.GET)
    public String media_object_1() {
        return "media-object-1";
    }
    
    
    //media-object-colunm-align-2
    @RequestMapping(value = "/media_object_colunm_align_2", method = RequestMethod.GET)
    public String media_object_colunm_align_2() {
        return "media-object-colunm-align-2";
    }
    
    
    // media-object-nested-3
    @RequestMapping(value = "/media_object_nested_3", method = RequestMethod.GET)
    public String media_object_nested_3() {
        return "media-object-nested-3";
    }
    
    
    //carousel-1
    @RequestMapping(value = "/carousel_1", method = RequestMethod.GET)
    public String carousel_1() {
        return "carousel-1";
    }
    
    
    
    //carousel-sildes-2
     @RequestMapping(value = "/carousel_sildes_2", method = RequestMethod.GET)
    public String carousel_sildes_2() {
        return "carousel-sildes-2";
    }
    
    
    //modal-1
     @RequestMapping(value = "/modal_1", method = RequestMethod.GET)
    public String modal_1() {
        return "modal-1";
    }
    
    
    //modal-small-2
    @RequestMapping(value = "/modal_small_2", method = RequestMethod.GET)
    public String modal_small_2() {
        return "modal-small-2";
    }
    
    //modal-large-3
     @RequestMapping(value = "/modal_large_3", method = RequestMethod.GET)
    public String modal_large_3() {
        return "modal-large-3";
    }
    
    
    //tooltip-1
     @RequestMapping(value = "/tooltip_1", method = RequestMethod.GET)
    public String tooltip_1() {
        return "tooltip-1";
    }
    
    //tooltip-position-2
     @RequestMapping(value = "/tooltip_position_2", method = RequestMethod.GET)
    public String tooltip_position_2() {
        return "tooltip-position-2";
    }
    
    //popover-1
    @RequestMapping(value = "/popover_1", method = RequestMethod.GET)
    public String popover_1() {
        return "popover-1";
    }
    
    //popover-position-2
    @RequestMapping(value = "/popover_position_2", method = RequestMethod.GET)
    public String popover_position_2() {
        return "popover-position-2";
    }
    
    //popover-dismissable-3
    @RequestMapping(value = "/popover_dismissable_3", method = RequestMethod.GET)
    public String popover_dismissable_3() {
        return "popover-dismissable-3";
    }
    
    //popover-4
    @RequestMapping(value = "/popover_4", method = RequestMethod.GET)
    public String popover_4() {
        return "popover-4";
    }
    
    //scrollspy-horizontal-1
    @RequestMapping(value = "/scrollspy_horizontal_1", method = RequestMethod.GET)
    public String scrollspy_horizontal_1() {
        return "scrollspy-horizontal-1";
    }
    
    //scrollspy-vertical-2
    @RequestMapping(value = "/scrollspy_vertical_2", method = RequestMethod.GET)
    public String scrollspy_vertical_2() {
        return "scrollspy-vertical-2";
    }
    
    //affix-horizontal-1
    @RequestMapping(value = "/affix_horizontal_1", method = RequestMethod.GET)
    public String affix_horizontal_1() {
        return "affix-horizontal-1";
    }
    
    //affix-vertical-2
    @RequestMapping(value = "/affix_vertical_2", method = RequestMethod.GET)
    public String affix_vertical_2() {
        return "affix-vertical-2";
    }
    
    
    //affix-scrollspy-mix-3
     @RequestMapping(value = "/affix_scrollspy_mix_3", method = RequestMethod.GET)
    public String affix_scrollspy_mix_3() {
        return "affix-scrollspy-mix-3";
    }
    
    //affix-scrollspy-vertical-mix-4
     @RequestMapping(value = "/affix_scrollspy_vertical_mix_4", method = RequestMethod.GET)
    public String affix_scrollspy_vertical_mix_4() {
        return "affix-scrollspy-vertical-mix-4";
    }
    
    //filter-table-1
    @RequestMapping(value = "/filter_table_1", method = RequestMethod.GET)
    public String filter_table_1() {
        return "filter-table-1";
    }
    
    //filter-list-2
    @RequestMapping(value = "/filter_list_2", method = RequestMethod.GET)
    public String filter_list_2() {
        return "filter-list-2";
    }
    
    //filter-dropdown-3
     @RequestMapping(value = "/filter_dropdown_3", method = RequestMethod.GET)
    public String filter_dropdown_3() {
        return "filter-dropdown-3";
    }
    
    //filter-anything-4
    @RequestMapping(value = "/filter_anything_4", method = RequestMethod.GET)
    public String filter_anything_4() {
        return "filter-anything-4";
    }
    
    
    //small-grid-1
    @RequestMapping(value = "/small_grid_1", method = RequestMethod.GET)
    public String small_grid_1() {
        return "small-grid-1";
    }
    
    
    //small-grid-2
    @RequestMapping(value = "/small_grid_2", method = RequestMethod.GET)
    public String small_grid_2() {
        return "small-grid-2";
    }
    
    
    //medium-grid-1
    @RequestMapping(value = "/medium_grid_1", method = RequestMethod.GET)
    public String medium_grid_1() {
        return "medium-grid-1";
    }
    
    
    //templates-blog-1
    @RequestMapping(value = "/templates_blog_1", method = RequestMethod.GET)
    public String templates_blog_1() {
        return "templates-blog-1";
    }
    
    
    //templates-portfoglio-2
    @RequestMapping(value = "/templates_portfoglio_2", method = RequestMethod.GET)
    public String templates_portfoglio_2() {
        return "templates-portfoglio-2";
    }
    
    //template-webpage-3
    @RequestMapping(value = "/template_webpage_3", method = RequestMethod.GET)
    public String template_webpage_3() {
        return "template-webpage-3";
    }
    
    //template-social-4
    @RequestMapping(value = "/template_social_4", method = RequestMethod.GET)
    public String template_social_4() {
        return "template-social-4";
    }
    
    
    //template-marketing-5
    @RequestMapping(value = "/template_marketing_5", method = RequestMethod.GET)
    public String template_marketing_5() {
        return "template-marketing-5";
    }
    
    
    //template-marketing-6
    @RequestMapping(value = "/template_marketing_6", method = RequestMethod.GET)
    public String template_marketing_6() {
        return "template-marketing-6";
    }
    
    
    //template-online-store-7
    @RequestMapping(value = "/template_online_store_7", method = RequestMethod.GET)
    public String template_online_store_7() {
        return "template-online-store-7";
    }
    
    
    //template-simply-me-8
    @RequestMapping(value = "/template_simply_me_8", method = RequestMethod.GET)
    public String template_simply_me_8() {
        return "template-simply-me-8";
    }
    
    
    //template-company-9
    @RequestMapping(value = "/template_company_9", method = RequestMethod.GET)
    public String template_company_9() {
        return "template-company-9";
    }
    
    
    //2jumbotron
    @RequestMapping(value = "/2jumbotron", method = RequestMethod.GET)
    public String jumbotron_2() {
        return "2jumbotron";
    }
    
    //3jumbotron
    @RequestMapping(value = "/3jumbotron", method = RequestMethod.GET)
    public String jumbotron_3() {
        return "3jumbotron";
    }
    
    
    
    //template-band-10
    @RequestMapping(value = "/template_band_10", method = RequestMethod.GET)
    public String template_band_10() {
        return "template-band-10";
    }
    
    
    //google-maps-1
    @RequestMapping(value = "/google_maps_1", method = RequestMethod.GET)
    public String google_maps_1() {
        return "google-maps-1";
    }
    
    
    //  //google-maps-custom-imag-2
    @RequestMapping(value = "/google_maps_custom_imag_2", method = RequestMethod.GET)
    public String google_maps_custom_imag_2() {
        return "google-maps-custom-imag-2";
    }
    
    
    
    //google-maps-finder-3
    @RequestMapping(value = "/google_maps_finder_3", method = RequestMethod.GET)
    public String google_maps_finder_3() {
        return "google-maps-finder-3";
    }
  
    
    //google-maps-auto-complete-search-4
    @RequestMapping(value = "/google_maps_auto_complete_search_4", method = RequestMethod.GET)
    public String google_maps_auto_complete_search_4() {
        return "google-maps-auto-complete-search-4";
    }
    
    //google-maps-search-hotel-autocomplete-5
    @RequestMapping(value = "/google_maps_search_hotel_autocomplete_5", method = RequestMethod.GET)
    public String google_maps_search_hotel_autocomplete_5() {
        return "google-maps-search-hotel-autocomplete-5";
    }
    
    
    
    
    

    
}
