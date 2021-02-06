/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=false, includeFieldNames=false)
public class Group {

    private String name;
}
