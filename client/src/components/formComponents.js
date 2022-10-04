import { TextField, Select, MenuItem } from "@mui/material";

import { Controller, useForm } from "react-hook-form";
import React from "react";

export const FormSelect = ({name, control, defaultValue="", rules, label, items, ...props}) => {
    const { setValue } = useForm();
    return ( 
    <Controller
        control={control}
        name={name}
        defaultValue={defaultValue}
        render={({ field: { onChange, value }, fieldState: { error } }) => {
            return <Select name={name}>
                {
                items.map((item) => (
                    <MenuItem key={item.key} value={item.value}>
                        {item.text}
                    </MenuItem>
                ))
                }
            </Select>
            }
        }
    />);
}

export const FormText = ({ name, control, defaultValue="", rules, label, ...props }) => {
    const { setValue } = useForm();
    return (
        <Controller
            name={name}
            control={control}
            defaultValue={defaultValue}
            render={({ field: { onChange, value }, fieldState: { error } }) => {
                if(!value && !!defaultValue) {setValue(name,defaultValue);}
                return (
                    <TextField 
                        name={name} 
                        onChange={onChange} 
                        value={value || defaultValue}
                        label={label} 
                        error={!!error} 
                        helperText={error ? error.message:null}
                        variant="standard"
                        required={!!rules?.required}
                        {...props}
                    />
                )
            }}
            rules={{...rules}}
        />
    );
};

export const FormTextArea = ({ name, control, label, defaultValue,rules, ...props }) => {
    const { setValue } = useForm();
    return (
        <Controller
            name={name}
            control={control}
            defaultValue={defaultValue||""}
            render={({ field: { onChange, value }, fieldState: { error } }) => {
                if(!value && !!defaultValue) {setTimeout(setValue(name,defaultValue));}
                return (
                    <TextField 
                        multiline 
                        rows={4} 
                        name={name} 
                        value={value || defaultValue} 
                        label={label} 
                        onChange={onChange} 
                        error={!!error} 
                        helperText={error ? error.message:null}
                        required={!!rules?.required}
                        {...props}
                    />
                )
            }}
            rules={{...rules}}
        />
    );
};