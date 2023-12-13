/* tslint:disable */
export interface ConcreteSolutionDto {
  id: string;
  name?: string;
  description?: string;
  concreteSolutionType?: 'FILE';
  qubitCount?: number;
  inputParameterFormat?: string;
  hasHeader?: boolean;
  hasMeasurment?: boolean;
  startPattern?: boolean;
  endPattern?: boolean;
}

