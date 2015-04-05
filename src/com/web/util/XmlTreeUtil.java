package com.web.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.web.entity.Model;
import com.web.entity.Packet;

public class XmlTreeUtil {
	
	
	private static Document document;
	public static  String SAVEPATH = ServletActionContext.getServletContext().getRealPath("/upload")+"//armodels.xml";
	public static String NAME="armodels.xml";
	  public  static void parseNodeToXML(List<Model> treeNodes,Packet p) throws IOException {
		
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory  .newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	             document = builder.newDocument();
	        } catch (ParserConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
		  
		  //Element root =document.createElement("armodels");
		  Element root =document.createElement("packet");
		  
	        document.appendChild(root);
	      //root  加说明文件，人物，人物背景。
	        Element desc= document.createElement("packetDesc");
	        desc.appendChild(document.createTextNode(p.getDescPic()));
	        root.appendChild(desc);
	        
	        //人物
	        Element character= document.createElement("character");
	        character.appendChild(document.createTextNode(p.getCharacter()));
	        root.appendChild(character);
	        //背景
	        
	        Element background= document.createElement("background");
	        background.appendChild(document.createTextNode(p.getBackground()));
	        root.appendChild(background);
	        
	        
	        
	        Element armodels =document.createElement("armodels");
	        root.appendChild(armodels);
	        for(int i=0;i<treeNodes.size();i++)
	        {
	        Element model = document.createElement("armodel");
	        
	        Element id = document.createElement("modelId");
	        id.appendChild(document.createTextNode( String.valueOf(treeNodes.get(i).getId()) ));
	        model.appendChild(id);
	        
	        Element name = document.createElement("modelName");
	        name.appendChild(document.createTextNode(treeNodes.get(i).getName()));
	        model.appendChild(name);
	        
	        //modelurl
	        Element url = document.createElement("modelUrl");
	        url.appendChild(document.createTextNode(treeNodes.get(i).getUrl()));
	        model.appendChild(url);
	        
	        //modelcode
	        Element codeid = document.createElement("modelCodeId");
	        codeid.appendChild(document.createTextNode(String.valueOf(treeNodes.get(i).getCode().getId())));
	        model.appendChild(codeid);
	        
	        //modelcodename
	        Element codename = document.createElement("modelCodeName");
	        codename.appendChild(document.createTextNode(treeNodes.get(i).getCode().getUrl()));
	        model.appendChild(codename);
	        
	        //modelSize
	        
	        Element size = document.createElement("modelSize");
	        size.appendChild(document.createTextNode( String.valueOf(treeNodes.get(i).getSize())));
	        model.appendChild(size);
	        
	        //modelRotation
	        
	        Element rotation = document.createElement("modelRotation");
	        rotation.appendChild(document.createTextNode(treeNodes.get(i).getRotate()));
	        model.appendChild(rotation);
	        
	        //modelTranslation
	        Element translation = document.createElement("modelTranslation");
	        translation.appendChild(document.createTextNode(treeNodes.get(i).getOffset()));
	        model.appendChild(translation);
	        
	        

	        
	        
	        //modelIsAnimation
	        
	        Element animation = document.createElement("modelIsAnimation");
	        animation.appendChild(document.createTextNode(String.valueOf(treeNodes.get(i).getAnimation())));
	        System.out.println("the animation is "+String.valueOf(treeNodes.get(i).getAnimation()));
	        model.appendChild(animation);
	        
	        //modelDiscription
	        
	        
	        Element discription = document.createElement("modelDiscription");
	        discription.appendChild(document.createTextNode(treeNodes.get(i).getInfo()));
	        model.appendChild(discription);
	        
	        

	        
	        armodels.appendChild(model);
	        
	        }
	        TransformerFactory tf = TransformerFactory.newInstance();
	        try {
	            Transformer transformer = tf.newTransformer();
	            DOMSource source = new DOMSource(document);
	            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         //   PrintWriter pw = new PrintWriter(new FileOutputStream(SAVEPATH+"//"+"armodels.xml"));
	            FileOutputStream os=new FileOutputStream(SAVEPATH);
	            PrintWriter pw = new PrintWriter(os);
	            StreamResult result = new StreamResult(pw);
	            transformer.transform(source, result);
                os.close();
	            System.out.println("生成XML文件成功!");
	        } catch (TransformerConfigurationException e) {
	            System.out.println(e.getMessage());
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (TransformerException e) {
	            System.out.println(e.getMessage());
	        }


		    
		 
	  }
}
