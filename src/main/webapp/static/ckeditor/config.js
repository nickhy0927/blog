/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	 config.language = 'zh-cn';
	 config.uiColor = '#F5F5DC';
	 config.toolbar = 'MyToolbar';
	 config.entities = true;
	 config.toolbar_MyToolbar =
        [
            { name: 'document', items : [ 'Source','-','DocProps','Preview','Print','-','Templates' ] },
	        { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
	        { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
	        '/',
	        { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
	        { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv',
	        '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
	        { name: 'links', items : [ 'Link','Unlink','Anchor' ] },
	        '/',
	        { name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
	        { name: 'colors', items : [ 'TextColor','BGColor' ] },
	        { name: 'tools', items : [ 'Maximize', 'ShowBlocks' ] },
	        { name: 'insert', items : [ 'Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'] }
        ];
	 config.image_previewText='图片预览';
	 var pathName = window.document.location.pathname;
	 var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	 if("/fscloudmanage" != projectName) {
		 projectName = "";
	 }
	 config.filebrowserImageUploadUrl = projectName + "/ckediter/uploadImageFile.web"; 
	 config.removeDialogTabs = 'image:advanced;image:Link';
};
