import { TextField, Select, MenuItem } from "@mui/material";

import { Controller, useForm } from "react-hook-form";
import React from "react";

export const FormSelect = ({name, control, defaultValue="", rules, label, options, ...props}) => {
    return ( 
    <Controller
        control={control}
        name={name}
        label={label}
        size="small"
        defaultValue={defaultValue}
        render={({ field: { onChange, value }, fieldState: { error } }) => {
            return <Select 
                    labelId="select-label"
                    label="age"
                    size="small"
                    name={name} 
                    onChange={onChange} 
                    displayEmpty
                    value={value||defaultValue} 
                    error={!!error} >
                    <MenuItem value="">
                        <em>{label}</em>
                    </MenuItem>
                    {
                    options.map((item) => (
                        <MenuItem key={item.value} value={item.value}>
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
                        error={error?.message} 
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

export const FormHidden = ({ name, control, label, value, defaultValue, rules, ...props }) => {
    const { setValue } = useForm();

    return (
        <Controller
            name={name}
            control={control}
            defaultValue={defaultValue||""}
            render={({ field: { onChange, value }, fieldState: { error } }) => {
                if(!value && !!defaultValue) {setTimeout(setValue(name,defaultValue));}
                return (
                    <input 
                        type="hidden"
                        name={name}
                        value={value || defaultValue} 
                        onChange={onChange} 
                        error={!!error} 
                        {...props}
                    />
                )
            }}
            rules={{...rules}}
        />
    );
};